package com.example.mamfoods.model

data class Product(
    val name: String,
    val price: String,
    val shortDescription: String,
    val ingredients: String,
    val imageUrl: String? = null // URL gambar yang didapat dari API Cloudinary
)
