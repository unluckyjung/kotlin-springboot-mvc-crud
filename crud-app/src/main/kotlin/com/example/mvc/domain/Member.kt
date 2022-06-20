package com.example.mvc.domain

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

class Member(
    val name: String,
    val age: Int,
    val phoneNumber: String
) {
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
    data class Request(
        val name: String,
        val age: Int,
        val phoneNumber: String
    )

    data class Response(
        val name: String,
        val age: Int,

        @JsonProperty("phone_number")
        val phoneNumber: String
    )
}
