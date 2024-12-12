package com.example.mamfoods.screens

import android.widget.Toast
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
import com.google.firebase.database.FirebaseDatabase
import androidx.compose.ui.platform.LocalContext // Import LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation

@Composable
fun AdminProfileScreen(onBackClick: () -> Unit) {
    val adminName = remember { mutableStateOf("") }
    val adminEmail = remember { mutableStateOf("") }
    val adminPassword = remember { mutableStateOf("") }
    val adminAddress = remember { mutableStateOf("") }
    val adminPhoneNumber = remember { mutableStateOf("") }

    val context = LocalContext.current
    val databaseUrl = "https://mamfoods-30204-default-rtdb.asia-southeast1.firebasedatabase.app/"
    val database = FirebaseDatabase.getInstance(databaseUrl)

    fun updateAdminProfile() {
        val name = adminName.value.trim()
        val email = adminEmail.value.trim()
        val password = adminPassword.value.trim()
        val address = adminAddress.value.trim()
        val phoneNumber = adminPhoneNumber.value.trim()

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || address.isEmpty() || phoneNumber.isEmpty()) {
            Toast.makeText(context, "Please fill in all fields correctly.", Toast.LENGTH_SHORT).show()
            return
        }

        val adminRef = database.getReference("admin/profile")

        val updatedProfile = mapOf(
            "name" to name,
            "email" to email,
            "password" to password,
            "address" to address,
            "phoneNumber" to phoneNumber
        )

        adminRef.updateChildren(updatedProfile)
            .addOnSuccessListener {
                Toast.makeText(context, "Profile updated successfully", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(context, "Failed to update profile: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(onClick = onBackClick) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Admin Profile",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = adminName.value,
            onValueChange = { adminName.value = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = adminEmail.value,
            onValueChange = { adminEmail.value = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = adminPassword.value,
            onValueChange = { adminPassword.value = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = adminAddress.value,
            onValueChange = { adminAddress.value = it },
            label = { Text("Address") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = adminPhoneNumber.value,
            onValueChange = { adminPhoneNumber.value = it },
            label = { Text("Phone Number") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { updateAdminProfile() },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Update Profile", color = Color.White)
        }
    }
}