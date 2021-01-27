package datas.model.gitlab.pull_requests


import com.fasterxml.jackson.annotation.JsonProperty

data class Target(
        @JsonProperty("avatar_url") val avatarUrl: String? = "",
        @JsonProperty("ci_config_path") val ciConfigPath: String? = "",
        @JsonProperty("default_branch") val defaultBranch: String? = "",
        @JsonProperty("description") val description: String? = "",
        @JsonProperty("git_http_url") val gitHttpUrl: String? = "",
        @JsonProperty("git_ssh_url") val gitSshUrl: String? = "",
        @JsonProperty("homepage") val homepage: String? = "",
        @JsonProperty("http_url") val httpUrl: String? = "",
        @JsonProperty("id") val id: Int = 0,
        @JsonProperty("name") val name: String? = "",
        @JsonProperty("namespace") val namespace: String? = "",
        @JsonProperty("path_with_namespace") val pathWithNamespace: String? = "",
        @JsonProperty("ssh_url") val sshUrl: String? = "",
        @JsonProperty("url") val url: String? = "",
        @JsonProperty("visibility_level") val visibilityLevel: Int? = 0,
        @JsonProperty("web_url") val webUrl: String? = ""
)