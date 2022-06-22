package com.example.mvc.controller

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("api/v1")
@RestController
class PutController {

    @PutMapping("/put-mapping/{age}")
    fun putMapping(
        @PathVariable age: Int
    ): ResponseEntity<String> {
        age.let {
            if (age < 20) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    "too young"
                )
            } else {
                logger.info("put something...")
                return ResponseEntity.ok("ok")
            }
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }
}
