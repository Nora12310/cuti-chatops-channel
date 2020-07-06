
import com.fasterxml.jackson.annotation.JsonProperty

data class Runner(
    @JsonProperty("active")
    val active: Boolean = false,
    @JsonProperty("description")
    val description: String? = "",
    @JsonProperty("id")
    val id: Int = 0,
    @JsonProperty("is_shared")
    val isShared: Boolean = false
)