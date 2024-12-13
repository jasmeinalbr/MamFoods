package com.example.mamfoods

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.mamfoods.userscreen.FoodItem
import com.example.mamfoods.userscreen.FoodItemCard
import com.example.mamfoods.userscreen.fetchFoodItems
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

@Composable
fun FetchProductData(productId: String) {
    // State untuk menyimpan data produk
    val productState = remember { mutableStateOf<FoodItem?>(null) }
    val context = LocalContext.current

    // Ambil data dari Firestore
    LaunchedEffect(Unit) {
        val db = Firebase.firestore
        val ref = db.collection("products").document(productId)

        ref.get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    // Parse data ke dalam FoodItem
                    val item = document.toObject(FoodItem::class.java)
                    productState.value = item
                } else {
                    Toast.makeText(context, "Document not found!", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener {
                Toast.makeText(context, "Error fetching data: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }

    // Tampilkan produk jika datanya sudah tersedia
    productState.value?.let { item ->
        FoodItemCard(item = item)
    } ?: run {
        Log.d("FetchData", "Product ID: $productId")

        // Loading State
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Loading...")
        }
    }
}


