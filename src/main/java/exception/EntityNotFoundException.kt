package exception


class EntityNotFoundException(
        message: String,
        val data: Map<String, Any>? = null
) : RuntimeException(message)