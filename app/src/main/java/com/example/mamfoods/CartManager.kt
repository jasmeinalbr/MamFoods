package com.example.mamfoods

import android.content.Context
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.example.mamfoods.userscreen.CartItem
import com.example.mamfoods.userscreen.FoodItem

class CartManager(val context : Context) {

    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()

    // Menambahkan item ke cart
    // saya ingin menyimpannya cart->userId->foodItem
    // cart->userId->foodItem->quantity
    // cart->userId->foodItem->price
    // cart->userId->foodItem->img
    // cart->userId->foodItem->name
    // cart->userId->foodItem->restaurant
    /*
    * package com.example.mamfoods.model

data class Product(
    val name: String,
    val price: Double,
    var image: String, // Ubah menjadi var
    val description: String,
    val ingredients: String,
    val restaurant: String
)

    * */


    fun addCartItem(userId: String, cartItem: CartItem) {
      /*  data class Product(
            val name: String,
            val price: Double,
            var image: String, // Ubah menjadi var
            val description: String,
            val ingredients: String,
            val restaurant: String
        )

        * */

        val cartItemRef = database.reference.child("carts").child(userId).child(cartItem.foodItem.name)
        cartItemRef.setValue(cartItem)
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
