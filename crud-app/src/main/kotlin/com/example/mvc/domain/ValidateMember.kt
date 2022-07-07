package com.example.mvc.domain

import com.example.mvc.annotation.ValidNickName
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import javax.validation.constraints.*

class ValidateMember(
    val name: String,
    val age: Int,
    val phoneNumber: String
) {
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
    data class Request(

        @field:ValidNickName(inValidNickNames = ["fortune", "cookie"])
        @field:NotBlank(message = "이름은 공백이나 null로 이루어질 수 없습니다.")
        @field:Size(min = 2, max = 10)
        val name: String,

        @field:PositiveOrZero
        @field:Max(100, message = "나이가 100살 을 넘으면 안됩니다")
        val age: Int,

        @field:Email
        val email: String? = null,

        val phoneNumber: String? = null
    ) {
        @AssertFalse(message = "jys는 블랙리스트 입니다.")
        private fun isBlackListName(): Boolean {
            return this.name == "jys"
        }
    }

    data class Response(
        @field:NotBlank
        @field:Size(min = 2, max = 10)
        val name: String,

        @field:PositiveOrZero
        val age: Int,

        @field:Email
        val email: String? = null,

        @JsonProperty("phone_number")
        val phoneNumber: String? = null
    )
}
