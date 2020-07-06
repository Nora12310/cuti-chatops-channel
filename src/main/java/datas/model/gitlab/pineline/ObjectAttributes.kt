import com.fasterxml.jackson.annotation.JsonProperty

data class ObjectAttributes(
        @JsonProperty("before_sha")
        val beforeSha: String? = "",
        @JsonProperty("created_at")
        val createdAt: String? = "",
        @JsonProperty("detailed_status")
        val detailedStatus: String? = "",
        @JsonProperty("duration")
        val duration: Int = 0,
        @JsonProperty("finished_at")
        val finishedAt: String? = "",
        @JsonProperty("id")
        val id: Int = 0,
        @JsonProperty("ref")
        val ref: String? = "",
        @JsonProperty("sha")
        val sha: String? = "",
        @JsonProperty("stages")
        val stages: List<String?> = listOf(),
        @JsonProperty("status")
        val status: String? = "",
        @JsonProperty("tag")
        val tag: Boolean = false
)