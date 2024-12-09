package com.example.mamfoods.data.api

import com.example.mamfoods.model.ApiResponse
import com.example.mamfoods.model.LoginRequest
import com.example.mamfoods.model.LoginResponse
import com.example.mamfoods.model.SignUpRequest

import com.example.mamfoods.model.User
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse

    @POST("auth/register")
    suspend fun signUp(@Body signUpRequest: SignUpRequest): ApiResponse<SignUpRequest>
}
