package com.example.mamfoods.userscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mamfoods.R
import com.example.mamfoods.ui.theme.TitlePage
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import com.example.mamfoods.ui.theme.RedPrimary
import com.example.mamfoods.ui.theme.SubText

@Composable
fun OrderScreen(navController: NavController, profileData: ProfileData) {
    // State untuk input sementara di halaman ini
    var name by remember { mutableStateOf(profileData.name) }
    var address by remember { mutableStateOf(profileData.address) }
    var phone by remember { mutableStateOf(profileData.phone) }
    // State untuk mengatur apakah sedang dalam mode edit
    var isEditing by remember { mutableStateOf(false) }

    BoxWithConstraints (
        modifier = Modifier.fillMaxSize().background(Color.White)
    ) {
        val screenWidth = maxWidth
        val screenHeight = maxHeight

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(screenWidth * 0.05f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Back button
            IconButton(onClick = { navController.popBackStack() }) {
                Image(
                    painter = painterResource(id = R.drawable.backbutton),
                    contentDescription = "Back",
                    modifier = Modifier.size(24.dp) // Ukuran ikon
                )
            }
            Spacer(modifier = Modifier.height(screenHeight * 0.02f))

            // Title
            Text(
                text = "Your Order",
                style = TitlePage,
                fontSize = (screenWidth * 0.08f).value.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.02f))

            // Row untuk Name
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
            // Address
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
            // Row untuk Phone Number
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
            // payment
            Card(
                elevation = 2.dp, // Ketinggian bayangan
                shape = RoundedCornerShape(15.dp), // Bentuk sudut
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(screenHeight * 0.13f)
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Payment Method",
                        style = TitlePage,
                        fontSize = (screenWidth * 0.05f).value.sp,
                        color = Color.Black
                    )
                    Image(
                        painter = painterResource(id = R.drawable.cod),
                        contentDescription = "COD",
                        modifier = Modifier
                            .width(106.dp)
                            .height(52.dp),
                        alignment = Alignment.CenterEnd
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Card(
                elevation = 2.dp, // Ketinggian bayangan
                shape = RoundedCornerShape(15.dp), // Bentuk sudut
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
            ) {
                // Button to place order
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(screenHeight * 0.07f)
                        .clip(RoundedCornerShape(15.dp)),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                    onClick = {
                        navController.navigate("ordersuccess")
                    },
                ) {
                    Text(
                        "Place My Order",
                        color = RedPrimary,
                        style = TitlePage,
                        fontSize = (screenWidth * 0.05f).value.sp,
                    )
                }
            }
            Spacer(modifier = Modifier.height(screenHeight * 0.01f))
        }
    }
}

@Composable
@Preview(showBackground = true)
fun OrderScreenPreview() {
    val profileData = ProfileData(
        name = "John Doe",
        address = "123 Main Street",
        phone = "1234567890",
        email = "1234@mail.com",
        password = "1234"
    )
    OrderScreen(navController = rememberNavController(), profileData = profileData)
}