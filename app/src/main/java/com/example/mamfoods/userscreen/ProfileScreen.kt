package com.example.mamfoods.userscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mamfoods.ui.theme.RedPrimary
import com.example.mamfoods.ui.theme.TitlePage

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
        BoxWithConstraints (
            modifier = Modifier.fillMaxSize().background(Color.White)
        ) {
            val screenWidth = maxWidth
            val screenHeight = maxHeight

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(innerPadding) // Gunakan innerPadding di sini
                    .padding(screenWidth * 0.05f),
            ) {
                Spacer(modifier = Modifier.weight(1f))

                // Title
                Text(
                    text = "Your Profile",
                    style = TitlePage,
                    fontSize = (screenWidth * 0.07f).value.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.weight(1f))

                // Profile fields (Name, Address, Email, Phone, Password)
                ProfileInfoField(
                    label = "Name",
                    value = name,
                    onValueChange = { name = it },
                    placeholder = "Enter Your Name",
                    isEditing = isEditing,
                    onEditingChange = { isEditing = it },  // Pass the callback here
                    isPassword = false
                )
                Spacer(modifier = Modifier.height(screenHeight * 0.01f))
                ProfileInfoField(
                    label = "Address",
                    value = address,
                    onValueChange = { address = it },
                    placeholder = "Add street information and home number",
                    isEditing = isEditing,
                    onEditingChange = { isEditing = it },  // Pass the callback here
                    isPassword = false
                )
                Spacer(modifier = Modifier.height(screenHeight * 0.01f))
                ProfileInfoField(
                    label = "Email",
                    value = email,
                    onValueChange = { email = it },
                    placeholder = "Enter Your Email",
                    isEditing = isEditing,
                    onEditingChange = { isEditing = it },  // Pass the callback here
                    isPassword = false
                )
                Spacer(modifier = Modifier.height(screenHeight * 0.01f))
                ProfileInfoField(
                    label = "Phone",
                    value = phone,
                    onValueChange = { phone = it },
                    placeholder = "Enter Your Phone",
                    isEditing = isEditing,
                    onEditingChange = { isEditing = it },  // Pass the callback here
                    isPassword = false
                )
                Spacer(modifier = Modifier.height(screenHeight * 0.01f))
                ProfileInfoField(
                    label = "Password",
                    value = password,
                    onValueChange = { password = it },
                    placeholder = "Enter Your Password",
                    isEditing = isEditing,
                    onEditingChange = { isEditing = it },  // Pass the callback here
                    isPassword = true
                )
                Spacer(modifier = Modifier.weight(1f))

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
                            .height(screenHeight * 0.07f)
                            .clip(RoundedCornerShape(15.dp)),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                        onClick = {
                            // Simpan data dan ubah status editing
                            isEditing = false // Setelah menyimpan, status edit menjadi false
                        },
                    ) {
                        Text(
                            "Save Information",
                            color = RedPrimary,
                            style = TitlePage,
                            fontSize = (screenWidth * 0.05f).value.sp,
                        )
                    }
                }
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
