package com.example.mvc.domain

class Member(
    val name: String,
    val age: Int,
    val phoneNumber: String
) {
    data class Request(
        val name: String,
        val age: Int,
        val phoneNumber: String
    )

    data class Response(
        val name: String,
        val age: Int,
        val phoneNumber: String
    )
}
