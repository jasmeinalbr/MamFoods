package com.example.mamfoods

// Data class untuk produk
data class Product(
    val name: String,         // Nama produk
    val restaurant: String,   // Restoran asal produk
    val price: Int            // Harga produk dalam integer (misalnya dalam ribuan)
)

// Data class untuk banner
data class Banner(
    val imageUrl: String      // URL gambar banner
)
