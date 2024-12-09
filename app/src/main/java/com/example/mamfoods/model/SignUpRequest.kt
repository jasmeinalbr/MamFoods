package com.example.mamfoods.model

data class SignUpRequest(
    val name: String,
    val email: String,
    val password: String,
    val confirmPassword: String? = null, // Digunakan jika Anda ingin memverifikasi konfirmasi password
    val profileImageUrl: String? = null // Menambahkan gambar profil jika diperlukan
)
