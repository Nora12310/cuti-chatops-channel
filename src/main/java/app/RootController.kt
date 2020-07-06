package app

import exception.EntityNotFoundException
import lombok.AllArgsConstructor
import datas.model.Response
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
@AllArgsConstructor
class RootController {

    @GetMapping("/")
    fun index(): ResponseEntity<*> {
        return Response.error<Any>(HttpStatus.BAD_REQUEST, EntityNotFoundException(
                message = "Cuti not support this url."
        ))
    }
}