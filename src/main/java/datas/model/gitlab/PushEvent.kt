package datas.model.gitlab


import com.fasterxml.jackson.annotation.JsonProperty

data class PushEvent(
        @JsonProperty("after") val after: String,
        @JsonProperty("before") val before: String,
        @JsonProperty("checkout_sha") val checkoutSha: String,
        @JsonProperty("commits") val commits: List<Commit>,
        @JsonProperty("event_name") val eventName: String,
        @JsonProperty("message") val message: String?,
        @JsonProperty("object_kind") val objectKind: String,
        @JsonProperty("project") val project: ProjectGitlab,
        @JsonProperty("project_id") val projectId: Int,
        @JsonProperty("ref") val ref: String,
        @JsonProperty("repository") val repository: Repository,
        @JsonProperty("total_commits_count") val totalCommitsCount: Int,
        @JsonProperty("user_avatar") val userAvatar: String,
        @JsonProperty("user_email") val userEmail: String,
        @JsonProperty("user_id") val userId: Int,
        @JsonProperty("user_name") val userName: String,
        @JsonProperty("user_username") val userUsername: String
)