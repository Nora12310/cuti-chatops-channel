package datas.model

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.mongodb.core.index.Indexed
import java.math.BigInteger
import java.security.MessageDigest

data class Project(
        @JsonProperty("git_url") val gitUrl: String,
        @JsonProperty("room_id") val roomId: String,
        @Indexed(unique = true) val key: String,
        val name: String
) {
    companion object {
        fun generateId(url: String): String {
            return String.format("%032x",
                    BigInteger(1, MessageDigest.getInstance("MD5")
                            .digest(url.toByteArray(Charsets.UTF_8))))
        }
    }
}