package com.example.mvc.controller

import com.example.mvc.domain.Member
import org.springframework.web.bind.annotation.*

@RequestMapping("api/v1")
@RestController
class PostController {

    @PostMapping("/post-sample")
    fun postSample(): String {
        return "post-sample"
    }

    @RequestMapping(method = [RequestMethod.GET], path = ["/duplicate-path"])
    fun duplicateGet(): String {
        return "duplicate-get"
    }

    @RequestMapping(method = [RequestMethod.POST], path = ["/duplicate-path"])
    fun duplicatePost(): String {
        return "duplicate-post"
    }

    @PostMapping("/post-mapping/object")
    fun postMapping(@RequestBody request: Member.Request): Member.Response {
        return Member.Response(
            name = request.name,
            age = request.age,
            phoneNumber = request.phoneNumber
        )
    }
}