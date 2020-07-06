package repository

import datas.entity.RoomEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface RoomRepository : MongoRepository<RoomEntity, String>
