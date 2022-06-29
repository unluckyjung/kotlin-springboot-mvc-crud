package com.example.mvc.controller

import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.constraints.Min
import javax.validation.constraints.Positive
import javax.validation.constraints.Size

@RestController
@RequestMapping("/api/v1/validate")
@Validated
class ValidateController1 {

    @PostMapping("/sample1")
    fun fun1(
        @Positive(message = "나이는 1살 이상이여야 합니다.")
        @Min(value = 20, message = "나이는 20살이상 이여야 합니다.")
        @RequestParam age: Int
    ): String {
        return "입력받은 나이 :$age"
    }

    @PostMapping("/sample1/{name}")
    fun fun2(
        @Size(min = 2, max = 10)
        @PathVariable name: String
    ): String {
        return "입력받은 이름 :$name"
    }
}
