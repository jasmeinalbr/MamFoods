package com.example.mamfoods.model

data class SignUpRequest(
    val email: String,
    val password: String,
    val nama: String,
    val role: String = "user",
    val profileImageUrl: String? = null
)
