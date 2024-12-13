package com.example.mamfoods

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.example.mamfoods.userscreen.CartItem
import com.example.mamfoods.userscreen.FoodItem

class CartManager {

    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()

    // Menambahkan item ke cart
    fun addCartItem(userId: String, cartItem: CartItem) {
        val cartRef = database.reference.child("carts").child(userId).child(cartItem.foodItem.name)

        cartRef.setValue(cartItem)
            .addOnSuccessListener {
                println("Cart item added successfully!")
            }
            .addOnFailureListener {
                println("Failed to add cart item: ${it.message}")
            }
    }

    // Menghapus item dari cart
    fun removeCartItem(userId: String, productId: String) {
        val cartItemRef = database.reference.child("carts").child(userId).child(productId)
        cartItemRef.removeValue()
            .addOnSuccessListener {
                println("Cart item removed successfully!")
            }
            .addOnFailureListener {
                println("Failed to remove cart item: ${it.message}")
            }
    }

    // Memperbarui quantity item di cart
    fun updateCartItem(userId: String, productId: String, updatedCartItem: CartItem) {
        val cartItemRef = database.reference.child("carts").child(userId).child(productId)
        cartItemRef.setValue(updatedCartItem)
            .addOnSuccessListener {
                println("Cart item updated successfully!")
            }
            .addOnFailureListener {
                println("Failed to update cart item: ${it.message}")
            }
    }

    // Mendapatkan semua item cart milik pengguna
    fun getCartItems(userId: String, callback: (List<CartItem>) -> Unit) {
        val cartItemsRef = database.reference.child("carts").child(userId)
        cartItemsRef.get().addOnSuccessListener { snapshot ->
            val cartItems = mutableListOf<CartItem>()
            for (childSnapshot in snapshot.children) {
                val cartItem = childSnapshot.getValue(CartItem::class.java)
                cartItem?.let { cartItems.add(it) }
            }
            callback(cartItems)  // Mengembalikan item cart
        }.addOnFailureListener {
            println("Failed to retrieve cart items: ${it.message}")
        }
    }
}
