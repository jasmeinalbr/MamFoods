package com.example.mamfoods.userscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mamfoods.R
import com.example.mamfoods.ui.theme.LightGrayColor
import com.example.mamfoods.ui.theme.RedPrimary
import com.example.mamfoods.ui.theme.SubText
import com.example.mamfoods.ui.theme.TitlePage
import com.example.mamfoods.ui.theme.White

@Composable
fun ProfileScreen(navController: NavController, profileData: ProfileData) {
    val selectedRoute = remember { mutableStateOf("profile") }

    // State untuk input sementara di halaman ini
    var name by remember { mutableStateOf(profileData.name) }
    var address by remember { mutableStateOf(profileData.address) }
    var email by remember { mutableStateOf(profileData.email) }
    var phone by remember { mutableStateOf(profileData.phone) }
    var password by remember { mutableStateOf(profileData.password) }

    // State untuk mengatur apakah sedang dalam mode edit
    var isEditing by remember { mutableStateOf(false) }

    Scaffold(
        bottomBar = {
            ButtonNavComponent(navController, selectedRoute)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(innerPadding) // Gunakan innerPadding di sini
                .padding(horizontal = 20.dp) // Tambahan padding jika perlu
        ) {
            Spacer(modifier = Modifier.height(60.dp))

            // Title
            Text(
                text = "Your Profile",
                style = TitlePage,
                fontSize = 32.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Profile fields (Name, Address, Email, Phone, Password)
            ProfileInfo(
                label = "Name",
                value = name,
                onValueChange = { name = it },
                placeholder = "Enter Your Name",
                isEditing = isEditing,
                onEditingChange = { isEditing = it },  // Pass the callback here
                isPassword = false
            )
            Spacer(modifier = Modifier.height(8.dp))
            ProfileInfo(
                label = "Address",
                value = address,
                onValueChange = { address = it },
                placeholder = "Add street information and home number",
                isEditing = isEditing,
                onEditingChange = { isEditing = it },  // Pass the callback here
                isPassword = false
            )
            Spacer(modifier = Modifier.height(8.dp))
            ProfileInfo(
                label = "Email",
                value = email,
                onValueChange = { email = it },
                placeholder = "Enter Your Email",
                isEditing = isEditing,
                onEditingChange = { isEditing = it },  // Pass the callback here
                isPassword = false
            )
            Spacer(modifier = Modifier.height(8.dp))
            ProfileInfo(
                label = "Phone",
                value = phone,
                onValueChange = { phone = it },
                placeholder = "Enter Your Phone",
                isEditing = isEditing,
                onEditingChange = { isEditing = it },  // Pass the callback here
                isPassword = false
            )
            Spacer(modifier = Modifier.height(8.dp))
            ProfileInfo(
                label = "Password",
                value = password,
                onValueChange = { password = it },
                placeholder = "Enter Your Password",
                isEditing = isEditing,
                onEditingChange = { isEditing = it },  // Pass the callback here
                isPassword = true
            )
            Spacer(modifier = Modifier.height(24.dp))

            Card(
                elevation = 4.dp, // Ketinggian bayangan
                shape = RoundedCornerShape(15.dp), // Bentuk sudut
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
            ) {
                // Button to save
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(65.dp)
                        .padding(vertical = 8.dp)
                        .clip(RoundedCornerShape(15.dp)),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                    onClick = {
                        // Simpan data dan ubah status editing
                        isEditing = false // Setelah menyimpan, status edit menjadi false
                    },
                ) {
                    Text("Save Information", color = RedPrimary, style = TitlePage, fontSize = 16.sp)
                }
            }
        }
    }
}

@Composable
fun ProfileInfo(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    onEditingChange: (Boolean) -> Unit, // Add this callback to update `isEditing`
    isEditing: Boolean,
    isPassword: Boolean
) {
    var temporaryValue by remember { mutableStateOf(value) } // Menyimpan nilai sementara
    var passwordVisible by remember { mutableStateOf(false) } // State untuk visibilitas password

    // Menggunakan visualTransformation untuk password
    val visualTransformation = if (passwordVisible) {
        VisualTransformation.None
    } else {
        PasswordVisualTransformation()
    }

    Card(
        elevation = 2.dp, // Ketinggian bayangan
        shape = RoundedCornerShape(15.dp), // Bentuk sudut
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start // Mengatur agar elemen mulai dari kiri
        ) {
            // Label
            Text(
                text = label,
                style = TitlePage,
                color = Color.Black,
                fontSize = 20.sp
            )
            if (isPassword) {
                OutlinedTextField(
                    value = if (isEditing) temporaryValue else value, // Nilai yang ditampilkan di field
                    onValueChange = { newValue -> // Callback untuk menangani perubahan input
                        if (isEditing) {
                            temporaryValue = newValue // Update nilai sementara jika dalam mode edit
                        }
                    },
                    enabled = isEditing, // TextField hanya aktif ketika mode edit aktif
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically),
                    placeholder = { // Placeholder menggunakan lambda
                        Text(
                            text = placeholder,
                            style = SubText,
                            color = Color.Gray,
                            fontSize = 12.sp
                        )
                    },
                    visualTransformation = visualTransformation, // Untuk menyembunyikan/memunculkan password
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.Black,
                        focusedBorderColor = Color.White, // Hilangkan border fokus
                        unfocusedBorderColor = Color.White // Hilangkan border tidak fokus
                    ),
                    trailingIcon = if (isEditing) { // Tampilkan ikon hanya jika mode edit aktif
                        {
                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                val icon = if (passwordVisible) R.drawable.eye else R.drawable.eyeoff
                                val description = if (passwordVisible) "Hide password" else "Show password"
                                Icon(
                                    painter = painterResource(id = icon),
                                    contentDescription = description,
                                    tint = Color.Black,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                    } else null // Jika tidak dalam mode edit, trailingIcon bernilai null
                )
            }
            else {
                OutlinedTextField(
                    value = if (isEditing) temporaryValue else value,
                    onValueChange = { newValue ->
                        if (isEditing) {
                            temporaryValue = newValue
                        }
                    },
                    enabled = isEditing,
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically),
                    placeholder = {
                        Text(
                            text = placeholder,
                            style = SubText,
                            color = Color.Gray,
                            fontSize = 12.sp
                        )
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.Black,
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White
                    ),
                )
            }
            // Icon Edit
            IconButton(
                onClick = {
                    // Toggle status mode edit
                    onEditingChange(!isEditing) // Update `isEditing` state via callback

                    // Jika selesai mengedit, kita bisa melakukan sesuatu (misalnya simpan data sementara)
                    if (!isEditing) {
                        // Simpan data sementara ke data profil atau lanjutkan transaksi
                        onValueChange(temporaryValue) // Menyimpan nilai yang sudah diedit ke profil atau data transaksi
                    }
                }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.edit),
                    contentDescription = "Edit",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ProfileScreenPreview() {
    val profileData = ProfileData(
        name = "John Doe",
        address = "123 Main Street",
        phone = "1234567890",
        email = "1234@mail.com",
        password = "1234"
    )
    ProfileScreen(navController = rememberNavController(), profileData = profileData)
}
