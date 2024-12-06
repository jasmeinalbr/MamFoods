package com.example.mamfoods.network

import com.example.mamfoods.network.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val BASE_URL: String = "http://10.0.2.2:8080/"

    val retrofitInstance: Retrofit
        get() = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val apiService: ApiService
        get() {
            return retrofitInstance.create(ApiService::class.java)
        }
}