package com.example.mvc.controller

import com.example.mvc.annotation.ValidNickName
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.Positive


@Validated
@RequestMapping("api/v1/exception")
@RestController
class ExceptionController {

    @GetMapping("/basic")
    fun basic() {
        throw Exception("기본 예외 발생")
    }

    @GetMapping("/age")
    fun age(@Valid @RequestBody req: ValidAgeDto): String {
        return """
            age : ${req.age}
        """.trimIndent()
    }

    @GetMapping("/nickName")
    fun nickName(@Valid @RequestBody req: ValidNickNameDto): String {
        return """
            nickName : ${req.nickName}
        """.trimIndent()
    }

    @GetMapping("/nickName2")
    fun nickName2(@ValidNickName @RequestParam nickName: String): String {
        return """
            nickName : $nickName
        """.trimIndent()
    }
}

data class ValidAgeDto(
    @field:Positive
    val age: Int
)

data class ValidNickNameDto(
    @field:ValidNickName(inValidNickNames = ["fortune", "cookie"])
    val nickName: String
)