package datas.model.gitlab


import com.fasterxml.jackson.annotation.JsonProperty

data class Repository(
        @JsonProperty("description") val description: String,
        @JsonProperty("git_http_url") val gitHttpUrl: String,
        @JsonProperty("git_ssh_url") val gitSshUrl: String,
        @JsonProperty("homepage") val homepage: String,
        @JsonProperty("name") val name: String,
        @JsonProperty("url") val url: String,
        @JsonProperty("visibility_level") val visibilityLevel: Int
)