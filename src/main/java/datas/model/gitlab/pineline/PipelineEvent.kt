package datas.model.gitlab.pineline

import Build
import Commit
import ObjectAttributes
import Project
import User
import com.fasterxml.jackson.annotation.JsonProperty

data class PipelineEvent(
        @JsonProperty("builds")
        val builds: List<Build> = listOf(),
        @JsonProperty("commit")
        val commit: Commit = Commit(),
        @JsonProperty("object_attributes")
        val objectAttributes: ObjectAttributes = ObjectAttributes(),
        @JsonProperty("object_kind")
        val objectKind: String = "",
        @JsonProperty("project")
        val project: Project = Project(),
        @JsonProperty("user")
        val user: User = User()
) {
    fun findBuildByStage(stage: String): Build? {
        if (builds.isNotEmpty()) {
            builds.map { if (it.stage == stage) return it }
        }
        return null
    }
}