package com.example.mamfoods

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

// Sealed class to represent different states of data loading
sealed class ProductState {
    object Loading : ProductState()
    data class Success(val products: List<Product>) : ProductState()
    data class Error(val message: String) : ProductState()
}

// Product data class


class ProductViewModel : ViewModel() {
    private val _productState = MutableStateFlow<ProductState>(ProductState.Loading)
    val productState: StateFlow<ProductState> = _productState.asStateFlow()

    init {
        fetchProducts()
    }

    fun fetchProducts() {
        viewModelScope.launch {
            _productState.value = ProductState.Loading
            try {
                val products = fetchProductsFromFirestore()
                _productState.value = ProductState.Success(products)
            } catch (e: Exception) {
                _productState.value = ProductState.Error("Error fetching products: ${e.message}")
            }
        }
    }

    private suspend fun fetchProductsFromFirestore(): List<Product> {
        val firestore = FirebaseFirestore.getInstance()
        val snapshot = firestore.collection("products").get().await()

        return snapshot.documents.mapNotNull { document ->
            val id = document.id
            val name = document.getString("name") ?: ""
            val price = document.getLong("price")?.toInt() ?: 0
            val description = document.getString("description") ?: ""
            val image = document.getString("image") ?: ""
            val ingredients = (document.get("ingredients") as? List<String>) ?: emptyList()

            Product(id, name, price, description, image, ingredients)
        }
    }
}