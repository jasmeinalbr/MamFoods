package com.example.mamfoods

interface AuthResponse {
    data object Success : AuthResponse
    data class Error(val message: String) : AuthResponse
}