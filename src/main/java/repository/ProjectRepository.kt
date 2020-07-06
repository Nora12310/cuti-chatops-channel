package repository

import datas.entity.ProjectEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ProjectRepository : MongoRepository<ProjectEntity, String> {

    @Query(value = "{'gitUrl' : ?0}")
    fun findByUrl(gitUrl: String): ProjectEntity?

    @Query(value = "{'key' : ?0}")
    fun findByKey(key: String): ProjectEntity?

    @Query(value = "{'roomId' : ?0}")
    fun findByRoomId(roomId: String): List<ProjectEntity>
}