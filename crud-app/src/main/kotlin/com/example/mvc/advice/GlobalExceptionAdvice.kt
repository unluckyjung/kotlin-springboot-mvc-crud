package com.example.mvc.advice

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.validation.ConstraintViolationException

@RestControllerAdvice
class GlobalExceptionAdvice {

    @ExceptionHandler(value = [Exception::class])
    fun basic(e: Exception): ResponseEntity<ExceptionDto> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ExceptionDto(e.toString(), value = null)
        )
    }

    @ExceptionHandler(value = [MissingServletRequestParameterException::class])
    fun requireNullExceptionHandler(e: MissingServletRequestParameterException): ResponseEntity<ExceptionDto> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ExceptionDto(
                message = "필수로 있어야하는 값에서 요청이 null 로 왔습니다.",
                field = e.parameterName,
                value = null,
            )
        )
    }

    @ExceptionHandler(value = [MethodArgumentNotValidException::class])
    fun validExceptionHandler(e: MethodArgumentNotValidException): ResponseEntity<ExceptionDto> {
        val fieldError = e.bindingResult.fieldError

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            fieldError?.let {
                ExceptionDto(
                    message = "by validated advice: ${fieldError.defaultMessage}",
                    field = fieldError.field,
                    value = fieldError.rejectedValue
                )
            }
        )
    }

    @ExceptionHandler(value = [ConstraintViolationException::class])
    fun validatedExceptionHandler(e: ConstraintViolationException): ResponseEntity<ExceptionDto> {
        val errorMsg = e.constraintViolations.firstOrNull()

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            errorMsg?.let {
                ExceptionDto(
                    "by validated advice: ${errorMsg.message}",
                    errorMsg.propertyPath.last().name,
                    errorMsg.invalidValue,
                )
            }
        )
    }
}


data class ExceptionDto(
    val message: String = "default Msg",
    val field: String = "default field",
    val value: Any?,
)
