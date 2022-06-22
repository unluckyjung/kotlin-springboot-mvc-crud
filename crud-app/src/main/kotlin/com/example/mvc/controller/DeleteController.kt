package com.example.mvc.controller

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*

@RequestMapping("api/v1")
@RestController
class DeleteController {

    // DELETE http://localhost:8080/api/v1/delete-mapping1?age=30&name=goodall&phone-number=010-1234-5678
    @DeleteMapping(path = ["delete-mapping1"])
    fun deleteMapping1(
        age: Int,
        @RequestParam(name = "name") name: String,
        @RequestParam(value = "phone-number") phoneNumber: String
    ): String {
        logger.info("deleteMapping1 (query parameter) name: $name called")
        return name
    }

    // DELETE http://localhost:8080/api/v1/delete-mapping2/30/goodall
    @DeleteMapping("delete-mapping2/{age}/{name}")
    fun deleteMapping2(
        @PathVariable age: Int,
        @PathVariable name: String
    ): String {
        logger.info("deleteMapping2 (pathVariable) name: $name called")
        return name
    }

    companion object {
        private val logger = LoggerFactory.getLogger(DeleteController::class.java)
    }
}
