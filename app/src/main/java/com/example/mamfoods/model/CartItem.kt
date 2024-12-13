package com.example.mamfoods.model

import com.example.mamfoods.userscreen.FoodItem


data class CartItem(
    val userId: String,        // User ID who added the item to the cart
    val productId: String,     // Unique product ID
    val foodItem: FoodItem,    // The FoodItem object
    val quantity: Int          // Quantity of the product
)
