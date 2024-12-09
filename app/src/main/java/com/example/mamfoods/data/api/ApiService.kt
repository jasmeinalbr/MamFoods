package com.example.mamfoods.data.api


import com.example.mamfoods.model.LoginRequest
import com.example.mamfoods.model.SignUpRequest
import com.example.mamfoods.model.User
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Body

interface ApiService {

    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): ApiResponse<User>

    @POST("auth/signup")
    suspend fun signUp(@Body signUpRequest: SignUpRequest): ApiResponse<User>


}
