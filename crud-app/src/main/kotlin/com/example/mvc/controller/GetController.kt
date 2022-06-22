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


    // name null = 500 에러 + NPE (kotlin not null)
    // age null = 400 에러 (required = false)
    @GetMapping("hello/query-params/without-annotation")
    fun helloQueryParamsWithoutAnnotation(
        name: String,
        @RequestParam age: Int,
    ): String {
        return "name: $name, age: $age"
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
}
