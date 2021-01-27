package datas.model.gitlab


import com.fasterxml.jackson.annotation.JsonProperty

data class Author(
    @JsonProperty("email") val email: String? = null,
    @JsonProperty("name") val name: String? = null
)