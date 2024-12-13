package com.example.mamfoods.model

data class Product(
    val name: String,
    val price: Double,
    var image: String, // Ubah menjadi var
    val description: String,
    val ingredients: String,
    val restaurant: String
)