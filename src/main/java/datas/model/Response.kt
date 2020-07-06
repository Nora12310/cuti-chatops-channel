package datas.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.time.LocalDateTime


@JsonInclude(JsonInclude.Include.NON_NULL)
data class Response<T>(
        val status: Int,
        val message: String,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
        val timestamp: LocalDateTime = LocalDateTime.now(),
        val result: T?
) {
    companion object {
        fun <T> error(status: HttpStatus, throwable: Throwable, result: T? = null): ResponseEntity<Response<T>> =
                ResponseEntity.status(status).body(Response(
                        status = status.value(),
                        message = throwable.localizedMessage,
                        timestamp = LocalDateTime.now(),
                        result = result
                ))

        fun <T> ok(result: T? = null): ResponseEntity<Response<T>> =
                ResponseEntity.status(HttpStatus.OK).body(
                        Response(
                                status = HttpStatus.OK.value(),
                                message = "Success",
                                timestamp = LocalDateTime.now(),
                                result = result
                        )
                )
    }
}