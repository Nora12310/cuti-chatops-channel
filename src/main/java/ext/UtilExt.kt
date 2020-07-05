package ext

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule

fun Any.toMap(): Map<String, Any> {
    val mapper = ObjectMapper().registerModule(KotlinModule())
    return mapper.convertValue(this, object :
            TypeReference<Map<String, Any>>() {})
}