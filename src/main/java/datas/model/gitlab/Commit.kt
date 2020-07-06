package datas.model.gitlab


import com.fasterxml.jackson.annotation.JsonProperty

data class Commit(
    @JsonProperty("added") val added: List<String>?,
    @JsonProperty("author") val author: Author?,
    @JsonProperty("id") val id: String?,
    @JsonProperty("message") val message: String?,
    @JsonProperty("modified") val modified: List<Any>?,
    @JsonProperty("removed") val removed: List<Any>?,
    @JsonProperty("timestamp") val timestamp: String?,
    @JsonProperty("url") val url: String?
)