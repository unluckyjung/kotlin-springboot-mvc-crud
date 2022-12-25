package com.example.mvc.controller

import com.example.mvc.domain.Member
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("api/v1")
@RestController
class GetController {

    @GetMapping("/hello", "/helloWorld")
    fun hello(): String {
        return "Hello kotlin"
    }

    @GetMapping("/hello/{name}/{age}")
    fun pathHello(
        @PathVariable name: String,
        @PathVariable age: Int
    ): String {
        return "Hello $name, your age is $age"
    }

    @GetMapping("/hello/{age}/default-test1", "/hello/default-test1")
    fun pathDefaultTest(
//        @PathVariable(required = false) age: Int = 10
//        @PathVariable(required = false) age: Int? = 10
        @PathVariable(name = "age", required = false) _age: Int?
    ): String {
        val age = _age ?: 10
        return "age: $age"
    }

    @GetMapping("/hello2/{name}/{age}")
    fun pathHello2(
        @PathVariable(name = "name") memberName: String,
        @PathVariable(value = "age") memberAge: Int
    ): String {
        return "Hello $memberName, your age is $memberAge"
    }

    @GetMapping("hello/query-params")
    fun helloQueryParams(
        @RequestParam name: String,
        @RequestParam age: Int,
        // 하이픈 소화 가능
        @RequestParam(name = "phone-number") phoneNumber: String,
    ): String {
        return "Hello $name your age is $age and your phone-number is $phoneNumber"
    }


    // name null = 500 에러 + NPE (required = false, kotlin not null)
    // age null = 400 에러 (required = true)
    @GetMapping("hello/query-params/without-annotation")
    fun helloQueryParamsWithoutAnnotation(
        name: String,
        @RequestParam age: Int,
    ): String {
        return "name: $name, age: $age"
    }


    // input null, return 77
    @GetMapping("hello/query-params/default-test1")
    fun helloQueryParamsDefaultTest1(
//        @RequestParam(defaultValue = "77", required = false) age: Int?,
        @RequestParam(defaultValue = "77", required = false) age: Int,  // NotNull 타입이지만 null 로 받아도 에러 발생 x (77이 먼저 차서 들어옴)
    ): String {
        return "age: $age"
    }

    // input null, return null
    @GetMapping("hello/query-params/default-test2")
    fun helloQueryParamsDefaultTest2(
        @RequestParam(required = false) age: Int? = 77,
    ): String {
        return "age: $age"
    }

    // http://localhost:8080/api/v1/hello/query-params/object?age=30&name=goodall&phoneNumber=010-1234-5678
    // get + requestBody 방식이 아님.
    @GetMapping("/hello/query-params/object")
    fun helloQueryParamsObject(@ModelAttribute memberRequest: Member.Request): ResponseEntity<Member.Response> {
        return ResponseEntity.ok(
            Member.Response(
                "hello ${memberRequest.name}", memberRequest.age, memberRequest.phoneNumber
            )
        )
    }

    @GetMapping("/hello/query-params/object/without-annotation")
    fun helloQueryParamsObjectWithoutAnnotation(memberRequest: Member.Request): ResponseEntity<Member.Response> {
        return ResponseEntity.ok(
            Member.Response(
                "hello ${memberRequest.name}", memberRequest.age, memberRequest.phoneNumber
            )
        )
    }


    @GetMapping("hello/query-params/map")
    fun helloQueryParamsMap(@RequestParam request: Map<String, Any>): Map<String, Any> {
        return request
    }

    @GetMapping("/list")
    fun listGet(@RequestParam(required = true) values: List<String>): List<String> {
        return values.map { "$it return" }.toList()
    }
}
