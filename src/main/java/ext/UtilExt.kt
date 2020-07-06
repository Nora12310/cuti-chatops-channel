package ext

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import java.text.SimpleDateFormat
import java.util.*

fun Any.toMap(): Map<String, Any> {
    val mapper = ObjectMapper().registerModule(KotlinModule())
    return mapper.convertValue(this, object :
            TypeReference<Map<String, Any>>() {})
}

fun Any?.toJson(): String? {
    return if (this != null) {
        val mapper = ObjectMapper()
        return mapper.writeValueAsString(this)
    } else null
}

fun String.toDate(dateFormat: String = "yyyy-MM-dd'T'HH:mm:ss'Z'", timeZone: TimeZone = TimeZone.getTimeZone("UTC")): Date {
    val parser = SimpleDateFormat(dateFormat, Locale.getDefault())
    parser.timeZone = timeZone
    return parser.parse(this)
}

fun Date.formatTo(dateFormat: String, timeZone: TimeZone = TimeZone.getDefault()): String {
    val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())
    formatter.timeZone = timeZone
    return formatter.format(this)
}