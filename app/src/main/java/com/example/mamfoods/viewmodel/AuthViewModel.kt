package com.example.mamfoods.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.mamfoods.data.repository.UserRepository
import com.example.mamfoods.model.LoginRequest
import com.example.mamfoods.model.SignUpRequest
import com.example.mamfoods.model.User
import com.google.firebase.auth.FirebaseAuth

open class AuthViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val userRepository = UserRepository()

    // Login functionality
    open fun login(request: LoginRequest, onSuccess: () -> Unit, onError: (String) -> Unit) {
        auth.signInWithEmailAndPassword(request.email, request.password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onSuccess()
                } else {
                    onError(task.exception?.message ?: "Login failed")
                }
            }
    }


    // Register functionality
    fun register(request: SignUpRequest, onSuccess: () -> Unit, onError: (String) -> Unit) {
        auth.createUserWithEmailAndPassword(request.email, request.password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userId = auth.currentUser?.uid ?: return@addOnCompleteListener
                    val user = User(
                        id = userId,
                        email = request.email,
                        displayName = request.name,
                        role = "user",
                        profileImageUrl = request.profileImageUrl
                    )
                    userRepository.saveUserData(user, onSuccess, onError)
                } else {
                    onError(task.exception?.message ?: "Registration failed.")
                }
            }
    }

}
