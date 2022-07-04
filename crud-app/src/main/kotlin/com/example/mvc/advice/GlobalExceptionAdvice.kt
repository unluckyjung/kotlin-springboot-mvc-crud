package com.example.mvc.advice

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.validation.ConstraintViolationException

@RestControllerAdvice
class GlobalExceptionAdvice {

    @ExceptionHandler(value = [Exception::class])
    fun basic(e: Exception): ResponseEntity<ExceptionDto> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ExceptionDto(e.toString())
        )
    }

    @ExceptionHandler(value = [MethodArgumentNotValidException::class])
    fun validExceptionHandler(e: MethodArgumentNotValidException): ResponseEntity<ExceptionDto> {
        val fieldError = e.bindingResult.fieldError

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ExceptionDto("by valid advice: ${fieldError?.defaultMessage} [${fieldError?.rejectedValue}]")
        )
    }

    @ExceptionHandler(value = [ConstraintViolationException::class])
    fun validatedExceptionHandler(e: ConstraintViolationException): ResponseEntity<ExceptionDto> {
        val errorMsg = e.constraintViolations.firstOrNull()
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ExceptionDto("by validated advice: ${errorMsg?.messageTemplate} [${errorMsg?.invalidValue}]")
        )
    }
}


data class ExceptionDto(
    val message: String
)
