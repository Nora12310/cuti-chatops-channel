package app

import exception.EntityNotFoundException
import model.Response
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class BaseDevOpsController {
    @RequestMapping("*")
    @Throws(EntityNotFoundException::class)
    fun apiIndex(): ResponseEntity<Response<Any>> {
        return Response.error(
                status = HttpStatus.BAD_REQUEST,
                throwable = EntityNotFoundException("Cutibot not support this url.")
        )
    }
}