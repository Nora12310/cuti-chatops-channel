import com.fasterxml.jackson.annotation.JsonProperty

data class ArtifactsFile(
        @JsonProperty("filename")
        val filename: String? = "",
        @JsonProperty("size")
        val size: Int = 0
)