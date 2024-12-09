package com.example.mamfoods.data.repository

import android.util.Log
import com.example.mamfoods.data.api.ApiClient
import com.example.mamfoods.model.ApiResponse
import com.example.mamfoods.model.LoginRequest
import com.example.mamfoods.model.LoginResponse
import com.example.mamfoods.model.User
import com.example.mamfoods.model.SignUpRequest

class UserRepository {

    suspend fun loginUser(loginRequest: LoginRequest): User? {
        return try {
            Log.d("UserRepository", "Logging in...")
            val response: LoginResponse = ApiClient.instance.login(loginRequest)
            // Cek apakah data pengguna tidak null
            Log.d("UserRepository", "Response: ${response.user}")

            if (response.user != null) {
                Log.d("UserRepository", "API login successful")
                Log.d("UserRepository", "Email: ${response.user.email}")
                Log.d("UserRepository", "Nama: ${response.user.nama}")
                Log.d("UserRepository", "Role: ${response.user.role}")
                Log.d("UserRepository", "Firebase UID: ${response.user.firebaseUid}")

                User(
                    id = response.user.firebaseUid, // Gunakan Firebase UID sebagai ID
                    email = response.user.email,
                    role = response.user.role,
                    displayName = response.user.nama,
                    profileImageUrl = "" // Sesuaikan dengan kebutuhan
                )
            } else {
                Log.e("UserRepository", "API error: User data is null ${response.message} ${response.user}")
                null
            }
        } catch (e: Exception) {
            Log.e("UserRepository", "Error during login: ${e.message}")
            null
        }
    }




    // Menyimpan data pengguna ke API (sign up)
    suspend fun saveUserData(SignUpRequest:SignUpRequest): Boolean {
        return try {
            Log.d("UserRepository", "Saving user data...")
            // Memanggil API signUp dan menerima respons
            val response: ApiResponse<SignUpRequest> = ApiClient.instance.signUp(SignUpRequest)
            Log.d("UserRepository", "Response: ${response.message}")

            // Mengecek apakah message berisi indikasi sukses
            if (response.message == "User successfully registered") {
                // Log jika sukses dan kembalikan true
                true
            } else {
                // Log pesan error jika status bukan sukses
                Log.e("UserRepository", "Error: ${response.message}")
                false
            }
        } catch (e: Exception) {
            // Menangani kesalahan dan mencatatnya
            Log.e("UserRepository", "Error saving user data: ${e.message}")
            false
        }
    }
}
