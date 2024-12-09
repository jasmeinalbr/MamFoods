package com.example.mamfoods.data.api

data class ApiResponse<T>(
    val data: T?,
    val error: String?
)
