package com.example.mvc.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("api/v1/error")
@RestController
class ErrorTestController {

    @GetMapping("/list-empty-string")
    fun listEmptyStringNullTest(@RequestBody dto: TestDto) {
        dto.list.forEach {
            // [1,2,""] 형태인 경우, "" 을 처리하다가 NPE 발생
            println(it)
        }
    }
}

data class TestDto(
    val list: List<Long>,
)
