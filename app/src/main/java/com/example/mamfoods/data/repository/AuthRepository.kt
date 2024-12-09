package com.example.mamfoods.data.repository

import com.example.mamfoods.data.api.ApiService
import com.example.mamfoods.model.LoginRequest
import com.example.mamfoods.model.SignUpRequest
import com.example.mamfoods.model.User

class AuthRepository(private val apiService: ApiService) {

    suspend fun login(loginRequest: LoginRequest) = apiService.login(loginRequest)

    suspend fun signUp(signUpRequest: SignUpRequest) = apiService.signUp(signUpRequest)
}

