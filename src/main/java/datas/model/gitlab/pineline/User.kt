
import com.fasterxml.jackson.annotation.JsonProperty

data class User(
    @JsonProperty("avatar_url")
    val avatarUrl: String? = "",
    @JsonProperty("name")
    val name: String? = "",
    @JsonProperty("username")
    val username: String? = ""
)