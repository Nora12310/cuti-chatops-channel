package datas.entity

import com.fasterxml.jackson.annotation.JsonProperty
import datas.model.GroupProfile
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document


@Document(value = "room")
data class RoomEntity(
        @JsonProperty("room_id") @Id @Indexed val roomId: String,
        val name: String,
        val thumbnail: String
) {
    companion object {
        fun convert(group: GroupProfile): RoomEntity {
            return RoomEntity(
                    roomId = group.groupId,
                    name = group.groupName,
                    thumbnail = group.pictureUrl
            )
        }
    }
}

