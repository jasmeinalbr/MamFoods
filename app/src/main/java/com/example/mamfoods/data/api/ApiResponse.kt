package com.example.mamfoods.model

data class ApiResponse<T>(
    val message: String, // Pesan dari API, seperti "User successfully registered"
   val  data: T? // Data yang diterima dari API, seperti data user
)


data class LoginResponse(
    val message: String,
    val user: UserData
)

data class UserData(
    val id: Int,
    val nama: String,
    val email: String,
    val role: String,
    val status: String,
    val firebaseUid: String
)

