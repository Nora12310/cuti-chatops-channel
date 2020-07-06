package datas.dao

import datas.entity.ProjectEntity
import repository.ProjectRepository

data class ProjectDao(private val repository: ProjectRepository) {

    fun save(entity: ProjectEntity) {
        repository.insert(entity)
    }

    fun getProjects(): List<ProjectEntity> {
        return repository.findAll()
    }

    fun find(id: String): ProjectEntity? {
        return repository.findById(id).orElseGet { null }
    }

    fun findByUrl(url: String): ProjectEntity? {
        return repository.findByUrl(url)
    }

    fun findByKey(key: String): ProjectEntity? {
        return repository.findByKey(key)
    }

    fun findByRoomId(roomId: String): List<ProjectEntity> {
        return repository.findByRoomId(roomId)
    }

    fun remove(id: String) {
        repository.deleteById(id)
    }
}