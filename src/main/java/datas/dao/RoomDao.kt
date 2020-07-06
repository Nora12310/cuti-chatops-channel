package datas.dao

import datas.entity.RoomEntity
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import repository.RoomRepository

/**
 * https://docs.spring.io/spring-data/mongodb/docs/1.2.0.RELEASE/reference/html/mongo.repositories.html
 */
@Component
class RoomDao @Autowired constructor(private val repository: RoomRepository) {
    private val log: Logger = LoggerFactory.getLogger(RoomDao::class.java)

    fun getRooms(): MutableList<RoomEntity> = repository.findAll()

    fun save(entity: RoomEntity) {
        repository.insert(entity)
    }

    fun remove(id: String) {
        repository.deleteById(id)
    }


}