package com.example.mvc.controller

import com.example.mvc.domain.Member
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
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
    fun postMapping(@RequestBody request: Member.Request): ResponseEntity<Member.Response> {

        logger.info("save something...")

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(
                Member.Response(
                    name = request.name,
                    age = request.age,
                    phoneNumber = request.phoneNumber
                )
            )
    }

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }
}
