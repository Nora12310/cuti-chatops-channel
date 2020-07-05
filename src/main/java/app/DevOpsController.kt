package app

import exception.EntityNotFoundException
import ext.toMap
import model.Line
import model.Response
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("api/v1")
class DevOpsController {

    @GetMapping("*")
    @Throws(EntityNotFoundException::class)
    fun apiIndex(): ResponseEntity<Response<Any>> {
        return Response.error(
                status = HttpStatus.BAD_REQUEST,
                throwable = EntityNotFoundException("Cutibot not support this url.")
        )
    }

    @GetMapping("/notify")
    @Throws(EntityNotFoundException::class)
    fun notify(@RequestParam(value = "name") name: String,
               @RequestParam(value = "age") age: Int): ResponseEntity<*> {

        val line = Line(age, name)
        return Response.ok(line)
    }
}