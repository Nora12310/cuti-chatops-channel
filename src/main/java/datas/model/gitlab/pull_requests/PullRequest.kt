package datas.model.gitlab.pull_requests

import ObjectAttributes
import Project
import User
import com.fasterxml.jackson.annotation.JsonProperty

data class PullRequest(
        @JsonProperty("event_type") val eventType: String,
        @JsonProperty("user") val user: User = User(),
        @JsonProperty("project") val project: Project = Project(),
        @JsonProperty("merge_status") val mergeStatus: String = "",
        @JsonProperty("url") val url: String = "",
        @JsonProperty("source_branch") val sourceBranch: String = "",
        @JsonProperty("state") val state: String = "",
        @JsonProperty("target_branch") val targetBranch: String = "",
        @JsonProperty("object_attributes") val objectAttributes: ObjectAttributes = ObjectAttributes(),
        @JsonProperty("target") val target: Target = Target(),
        @JsonProperty("last_commit") val lastCommit: LastCommit = LastCommit()
)