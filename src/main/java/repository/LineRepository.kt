package repository

import com.linecorp.bot.spring.boot.LineBotProperties
import datas.dao.ProjectDao
import datas.dao.RoomDao
import datas.entity.ProjectEntity
import datas.entity.RoomEntity
import datas.model.GroupProfile
import datas.network.LineApiGenerator
import exception.EntityNotFoundException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class LineRepository(
        private val lineProperties: LineBotProperties,
        private val roomDao: RoomDao,
        private val projectDao: ProjectDao,
        private val api: LineApiGenerator.APILink
) {
    private val log: Logger = LoggerFactory.getLogger(LineRepository::class.java)
    suspend fun getGroupProfile(groupId: String): GroupProfile {
        val token = "Bearer ${lineProperties.channelToken}"
        return api.getGroupProfile(token, groupId)
    }

    @Throws(EntityNotFoundException::class)
    suspend fun join(groupId: String) {
        if (groupId.isNotEmpty()) {
            withContext(Dispatchers.IO) {
                val group = getGroupProfile(groupId)
                roomDao.save(RoomEntity.convert(group))
            }
        } else throw EntityNotFoundException("'group_id' is empty")

    }

    suspend fun leave(groupId: String) {
        withContext(Dispatchers.IO) {
            ///remove room and project in room
            roomDao.remove(groupId)
            val projects = projectDao.findByRoomId(groupId)
            projects.map {
                projectDao.remove(it.id)
            }
        }
    }

    fun getRooms(): List<RoomEntity> {
        return roomDao.getRooms()
    }

    fun getProjects(): List<ProjectEntity> {
        return projectDao.getProjects()
    }

    suspend fun register(prj: ProjectEntity) {
        withContext(Dispatchers.IO) {
            val currentPrj: ProjectEntity? = projectDao.findByUrl(prj.key)
            // have just support 1 prj in 1 room now.
            if (currentPrj != null) {
                unregister(currentPrj.gitUrl)
            }
            projectDao.save(prj)
        }
    }

    suspend fun unregister(url: String): ProjectEntity? {
        if (url.isEmpty()) throw EntityNotFoundException("'key' is empty")
        return withContext(Dispatchers.IO) {
            val prj = projectDao.findByUrl(url)
            if (prj != null) {
                projectDao.remove(prj.id)
            } else throw EntityNotFoundException("Project not found in database!")
            return@withContext prj
        }
    }

    fun findProjectByKey(key: String?): ProjectEntity? {
        val keyName = key ?: return null
        return projectDao.findByKey(keyName)
    }

    fun findProjectByUrl(url: String?): ProjectEntity? {
        val validUrl = url ?: return null
        return projectDao.findByUrl(validUrl)
    }

}