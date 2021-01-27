import com.fasterxml.jackson.annotation.JsonProperty

/// https://github.com/google/gson/issues/1005
data class Build(
        @JsonProperty("artifacts_file")
        val artifactsFile: ArtifactsFile? = ArtifactsFile(),
        @JsonProperty("created_at")
        val createdAt: String? = "",
        @JsonProperty("finished_at")
        val finishedAt: String? = "",
        @JsonProperty("id")
        val id: Int = 0,
        @JsonProperty("manual")
        val manual: Boolean = false,
        @JsonProperty("name")
        val name: String? = "",
        @JsonProperty("runner")
        val runner: Runner? = Runner(),
        @JsonProperty("stage")
        val stage: String? = "",
        @JsonProperty("started_at")
        val startedAt: String? = "",
        @JsonProperty("status")
        val status: String? = "",
        @JsonProperty("user")
        val user: User = User(),
        @JsonProperty("when")
        val whenX: String? = ""
)