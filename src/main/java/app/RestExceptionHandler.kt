package app

import exception.EntityNotFoundException
import model.Response
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler


// https://www.toptal.com/java/spring-boot-rest-api-error-handling
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
class RestExceptionHandler : ResponseEntityExceptionHandler() {

    override fun handleHttpMessageNotReadable(
            ex: HttpMessageNotReadableException,
            headers: HttpHeaders,
            status: HttpStatus,
            request: WebRequest): ResponseEntity<Any> {
        val error = Response.error<Any>(HttpStatus.BAD_REQUEST, ex)
        return ResponseEntity.status(error.statusCode).body(error.body)
    }

    override fun handleMethodArgumentNotValid(
            ex: MethodArgumentNotValidException,
            headers: HttpHeaders,
            status: HttpStatus,
            request: WebRequest): ResponseEntity<Any> {
        val error = Response.error<Any>(HttpStatus.BAD_REQUEST, ex)
        return ResponseEntity.status(error.statusCode).body(error.body)
    }

    override fun handleMissingServletRequestParameter(
            ex: MissingServletRequestParameterException,
            headers: HttpHeaders, status: HttpStatus,
            request: WebRequest): ResponseEntity<Any> {
        val error = Response.error<Any>(HttpStatus.BAD_REQUEST, ex)
        return ResponseEntity.status(error.statusCode).body(error.body)
    }

    override fun handleHttpRequestMethodNotSupported(
            ex: HttpRequestMethodNotSupportedException,
            headers: HttpHeaders,
            status: HttpStatus,
            request: WebRequest): ResponseEntity<Any> {
        val error = Response.error<Any>(HttpStatus.BAD_REQUEST, ex)
        return ResponseEntity.status(error.statusCode).body(error.body)
    }

    @ExceptionHandler(EntityNotFoundException::class)
    fun handleEntityNotFound(ex: EntityNotFoundException): ResponseEntity<Any> {
        val error = Response.error<Any>(HttpStatus.BAD_REQUEST, ex, ex.data)
        return ResponseEntity.status(error.statusCode).body(error.body)
    }
}