
import com.fasterxml.jackson.annotation.JsonProperty

data class Commit(
    @JsonProperty("author")
    val author: Author = Author(),
    @JsonProperty("id")
    val id: String? = "",
    @JsonProperty("message")
    val message: String? = "",
    @JsonProperty("timestamp")
    val timestamp: String? = "",
    @JsonProperty("url")
    val url: String? = ""
)