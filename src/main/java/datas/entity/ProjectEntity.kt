package datas.entity

import com.fasterxml.jackson.annotation.JsonProperty
import datas.model.Project
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document(value = "project")
data class ProjectEntity(
        @Id val id: String,
        @JsonProperty("git_url") val gitUrl: String,
        @JsonProperty("room_id") val roomId: String,
        @Indexed(unique = true) val key: String,
        val name: String
) {

    companion object {
        fun convert(prj: Project): ProjectEntity {
            return ProjectEntity(
                    id = Project.generateId(prj.gitUrl),
                    roomId = prj.roomId,
                    name = prj.name,
                    gitUrl = prj.gitUrl,
                    key = prj.key
            )
        }
    }
}