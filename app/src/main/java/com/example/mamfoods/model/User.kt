package com.example.mamfoods.model

data class User(
    val id: String = "",
    val email: String = "",
    val displayName: String = "",
    val role: String = "user",
    val profileImageUrl: String? = null
)
