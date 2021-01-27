package datas.model.gitlab.pull_requests

import ObjectAttributes
import Project
import User
import com.fasterxml.jackson.annotation.JsonProperty

data class PullRequest(
        @JsonProperty("event_type") val eventType: String,
        @JsonProperty("user") val user: User = User(),
        @JsonProperty("project") val project: Project = Project(),
        @JsonProperty("object_attributes") val objectAttributes: PullRequestAttributes = PullRequestAttributes()
)