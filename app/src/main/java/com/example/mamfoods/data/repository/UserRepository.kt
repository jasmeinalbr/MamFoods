package com.example.mamfoods.data.repository

import com.example.mamfoods.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UserRepository {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val database: DatabaseReference = FirebaseDatabase.getInstance().reference.child("Users")

    // Get current user info
    fun getCurrentUser(): User? {
        return auth.currentUser?.let {
            User(
                id = it.uid,
                email = it.email ?: "",
                displayName = it.displayName ?: "Unknown",
                role = "user",
                profileImageUrl = null
            )
        }
    }

    // Save user data in Firebase
    fun saveUserData(user: User, onSuccess: () -> Unit, onError: (String) -> Unit) {
        database.child(user.id).setValue(user).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                onSuccess()
            } else {
                onError(task.exception?.message ?: "Failed to save user data.")
            }
        }
    }
}
