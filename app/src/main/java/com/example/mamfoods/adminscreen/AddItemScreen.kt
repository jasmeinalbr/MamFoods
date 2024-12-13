package com.example.mamfoods.adminscreen

import android.graphics.Bitmap
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.widget.Toast
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.mamfoods.model.Product
import com.example.mamfoods.ui.theme.TitleText
import com.google.firebase.database.FirebaseDatabase
import androidx.compose.ui.platform.LocalContext
import com.example.mamfoods.ImageUploadHandler

// Define the red gradient color
val RedGradient1 = Color(0xFFE85353)

@Composable
fun AddItemScreen(onBackClick: () -> Unit, onAddItemClick:()->Unit) {
    // Remember state for the input fields
    val itemName = remember { mutableStateOf("") }
    val itemPrice = remember { mutableStateOf("") }
    val shortDescription = remember { mutableStateOf("") }
    val ingredients = remember { mutableStateOf("") }
    val imageUri = remember { mutableStateOf<Uri?>(null) }

    val context = LocalContext.current
    val databaseUrl = "https://mamfoods-30204-default-rtdb.asia-southeast1.firebasedatabase.app"
    val database = FirebaseDatabase.getInstance(databaseUrl)

    // Function to upload product to Firebase Storage and then to the Firebase Database
    fun uploadProduct() {
        val name = itemName.value.trim()
        val priceStr = itemPrice.value.trim()
        val description = shortDescription.value.trim()
        val ingredientList = ingredients.value.trim()

        // Validate inputs
        if (name.isEmpty() || priceStr.isEmpty() || description.isEmpty() || ingredientList.isEmpty()) {
            Toast.makeText(context, "Please fill in all fields correctly.", Toast.LENGTH_SHORT).show()
            return
        }

        val price = priceStr.toDoubleOrNull()
        if (price == null || price <= 0) {
            Toast.makeText(context, "Please enter a valid price.", Toast.LENGTH_SHORT).show()
            return
        }

        val newProduct = Product(
            name = name,
            price = price,
            image = "",
            description = description,
            ingredients = ingredientList.split(",").map { it.trim() }.toString(),
            restaurant = "Mamfoods"
        )

        try {
            // Upload image to Cloudinary
            val imageUploadHandler = ImageUploadHandler()
            val bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, imageUri.value)
            imageUploadHandler.uploadImage(bitmap) { imageUrl ->
                newProduct.image = imageUrl
                uploadProductToDatabase(database, newProduct, context)
            }
        } catch (e: Exception) {
            Toast.makeText(context, "Failed to upload image: ${e.message}", Toast.LENGTH_SHORT).show()
            Log.e("ImageUpload", "Image upload error", e)  // Log image upload error for debugging
        }

    }

    // Activity result launcher for selecting image from gallery
    val launcher: ActivityResultLauncher<Intent> =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.let { uri ->
                    Log.d("ImageUri", "Selected URI: $uri")  // Log to check URI
                    imageUri.value = uri
                }
            } else {
                Log.d("ImageUri", "Image selection failed or cancelled")
            }
        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally, // Center content horizontally
        verticalArrangement = Arrangement.Top // Align content to the top of the screen
    ) {
        // Tombol Back di kiri atas
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(onClick = onBackClick) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Judul di tengah
        Text(
            text = "Add Item",
            style = TitleText,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Kolom inputan untuk Nama Item
        TextField(
            value = itemName.value,
            onValueChange = { itemName.value = it },
            label = { Text("Item Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Kolom inputan untuk Harga Item
        TextField(
            value = itemPrice.value,
            onValueChange = { itemPrice.value = it },
            label = { Text("Item Price") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_PICK).apply {
                    type = "image/*"
                }
                launcher.launch(intent)
            },
            colors = ButtonDefaults.buttonColors(containerColor = RedGradient1),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Upload Image", color = Color.White)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Kolom inputan untuk Deskripsi Singkat
        TextField(
            value = shortDescription.value,
            onValueChange = { shortDescription.value = it },
            label = { Text("Short Description") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Kolom inputan untuk Bahan-bahan
        TextField(
            value = ingredients.value,
            onValueChange = { ingredients.value = it },
            label = { Text("Ingredients (comma-separated)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Tombol untuk Menambahkan Item
        Button(
            onClick = { uploadProduct() },
            colors = ButtonDefaults.buttonColors(containerColor = RedGradient1),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Item", color = Color.White)
        }
    }
}

// Function to upload product to Firebase Database
fun uploadProductToDatabase(
    database: FirebaseDatabase,
    product: Product,
    context: android.content.Context
) {
    val myRef = database.getReference("products")
    val productId = myRef.push().key ?: return

    myRef.child(productId).setValue(product)
        .addOnSuccessListener {
            Toast.makeText(context, "Product added successfully", Toast.LENGTH_SHORT).show()
        }
        .addOnFailureListener { e ->
            Toast.makeText(context, "Failed to add product: ${e.message}", Toast.LENGTH_SHORT).show()
            Log.e("Firebase", "Database error", e)  // Log Firebase error for debugging
        }
}