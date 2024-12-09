package com.example.mamfoods.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope // Use this for better coroutine management
import com.example.mamfoods.data.repository.UserRepository
import com.example.mamfoods.model.LoginRequest
import com.example.mamfoods.model.SignUpRequest
import com.example.mamfoods.model.User
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

open class AuthViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val userRepository = UserRepository()

    // Login functionality
    fun login(
        request: LoginRequest,
        onSuccess: (User) -> Unit,
        onError: (String) -> Unit
    ) {
        Log.d("AuthViewModel", "Login method called with email: ${request.email}")

        // Pertama, coba login ke API
        CoroutineScope(Dispatchers.IO).launch {
            try {
                Log.d("AuthViewModel", "Logging in...")
                val apiResponse = userRepository.loginUser(request) // Fungsi untuk login ke API

                withContext(Dispatchers.Main) {
                    if (apiResponse != null) {
                        Log.d("AuthViewModel", "API login successful: ${apiResponse.email}")

                        // Jika berhasil di API, lanjutkan login ke Firebase
                        auth.signInWithEmailAndPassword(request.email, request.password)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    Log.d("AuthViewModel", "Firebase login successful")
                                    val user = User(
                                        id = auth.currentUser?.uid.orEmpty(),
                                        email = apiResponse.email,
                                        displayName = apiResponse.displayName,
                                        role = apiResponse.role,
                                        profileImageUrl = apiResponse.profileImageUrl
                                    )
                                    onSuccess(user)
                                } else {
                                    Log.e(
                                        "AuthViewModel",
                                        "Firebase login failed: ${task.exception?.message}"
                                    )
                                    onError(task.exception?.message ?: "Firebase login failed")
                                }
                            }
                    } else {
                        Log.e("AuthViewModel", "API login failed")
                        onError("API login failed")
                    }
                }
            } catch (e: Exception) {
                Log.e("AuthViewModel", "Error during login: ${e.message}")
                withContext(Dispatchers.Main) {
                    onError("Error during login: ${e.message}")
                }
            }
        }
    }


    // Register functionality
    // Register functionality
    fun register(
        request: SignUpRequest,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        Log.d("AuthViewModel", "Register method called with email: ${request.email}")
        Log.d("AuthViewModel", "Register method called with name: ${request.nama}")

        val signUpRequest = SignUpRequest(
            email = request.email,
            password = request.password,
            nama = request.nama,
            role = "user"
        )
        // Kirim data pengguna ke API terlebih dahulu
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Kirim data pengguna ke API (signUp) sebelum Firebase
                val isApiSuccess = userRepository.saveUserData(signUpRequest)

                withContext(Dispatchers.Main) {
                    if (isApiSuccess) {
                        // Setelah berhasil di API, baru mendaftar di Firebase
                        auth.createUserWithEmailAndPassword(request.email, request.password)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    // Buat objek User dengan Firebase UID
                                    val user = User(
                                        id = auth.currentUser?.uid.orEmpty(),
                                        email = request.email,
                                        displayName = request.nama,
                                        role = "user",
                                        profileImageUrl = ""
                                    )
                                    // Simpan informasi pengguna ke Firebase (ini akan bergantung pada kebutuhan Anda)
                                    onSuccess()
                                } else {
                                    Log.d(
                                        "AuthViewModel",
                                        "Firebase registration failed: ${task.exception?.message}"
                                    )
                                    onError(
                                        task.exception?.message ?: "Firebase registration failed"
                                    )
                                }
                            }
                    } else {
                        Log.e("AuthViewModel", "API error: Failed to save user data")
                        onError("API error: Failed to save user data")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Log.e("AuthViewModel", "Error during API call: ${e.message}")
                    onError("Error during API call: ${e.message}")
                }
            }
        }
    }
}