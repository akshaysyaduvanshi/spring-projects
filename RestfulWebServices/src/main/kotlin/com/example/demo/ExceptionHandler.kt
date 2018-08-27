package com.example.demo

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.*

@ResponseStatus(HttpStatus.NOT_FOUND)
class UserNotFoundException(val message1: String): RuntimeException(message1) {

}

data class ExceptionResponse(var date: Date, var message: String, var details: String)
@ControllerAdvice
@RestController
class DefaultExceptionHandler: ResponseEntityExceptionHandler() {
    @ExceptionHandler(Exception::class)
    fun handleAllException(exception: Exception, request: WebRequest): ResponseEntity<Any> {
        val response = ExceptionResponse(Date(), exception.message!!, request.getDescription(false))
        return ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(UserNotFoundException::class)
    fun handleUserNotFoundException(exception: UserNotFoundException, request: WebRequest): ResponseEntity<Any> {
        val response = ExceptionResponse(Date(), exception.message!!, request.getDescription(false))
        return ResponseEntity(response, HttpStatus.NOT_FOUND)
    }

    override fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        val response = ExceptionResponse(Date(), "Validation failed", ex.bindingResult.toString())
        return ResponseEntity(response, HttpStatus.BAD_REQUEST)
    }
}
