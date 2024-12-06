package com.example.mamfoods.network

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @get:GET("api/users")
    val users: Call<List<Any?>?>?
}