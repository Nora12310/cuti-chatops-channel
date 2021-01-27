package datas.model.gitlab.pull_requests

import com.fasterxml.jackson.annotation.JsonProperty

data class PullRequestAttributes(
        @JsonProperty("merge_error") val mergeError: String? = "",
        @JsonProperty("merge_status") val mergeStatus: String = "",
        @JsonProperty("url") val url: String = "",
        @JsonProperty("source_branch") val sourceBranch: String = "",
        @JsonProperty("state") val state: String = "",
        @JsonProperty("target_branch") val targetBranch: String = "",
        @JsonProperty("target") val target: Target = Target(),
        @JsonProperty("last_commit") val lastCommit: LastCommit = LastCommit()
)