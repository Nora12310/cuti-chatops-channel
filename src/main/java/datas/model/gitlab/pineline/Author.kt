
import com.fasterxml.jackson.annotation.JsonProperty

data class Author(
    @JsonProperty("email")
    val email: String? = "",
    @JsonProperty("name")
    val name: String? = ""
)