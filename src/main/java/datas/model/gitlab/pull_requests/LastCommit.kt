package datas.model.gitlab.pull_requests


import com.fasterxml.jackson.annotation.JsonProperty
import datas.model.gitlab.Author

data class LastCommit(
        @JsonProperty("author") val author: Author = Author(),
        @JsonProperty("id") val id: String = "",
        @JsonProperty("message") val message: String = "",
        @JsonProperty("timestamp") val timestamp: String = "",
        @JsonProperty("url") val url: String = ""
)