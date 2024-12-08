package com.example.mamfoods.userscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Back button
        IconButton(onClick = { navController.popBackStack() }) {
            Image(
                painter = painterResource(id = R.drawable.backbutton),
                contentDescription = "Back",
                modifier = Modifier.size(24.dp) // Ukuran ikon
            )
        }

        // Title
        Text(
            text = "Your Order",
            style = TitlePage,
            fontSize = 32.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Row untuk Name
        ProfileField(
            label = "Name",
            value = name,
            onValueChange = { name = it },
            placeholder = "Enter Your Name"
        )

        // Address
        ProfileField(
            label = "Address",
            value = address,
            onValueChange = { address = it },
            placeholder = "Add street information and home number"
        )

        // Row untuk Phone Number
        ProfileField(
            label = "Phone",
            value = phone,
            onValueChange = { phone = it },
            placeholder = "Enter Your Phone"
        )

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
                        .height(100.dp)
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Payment Method",
                        style = TitlePage,
                        fontSize = 20.sp,
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

            Spacer(modifier = Modifier.height(16.dp))

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
                    .height(65.dp)
                    .padding(vertical = 8.dp)
                    .clip(RoundedCornerShape(15.dp)),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                onClick = {
                    navController.navigate("ordersuccess")
                },
                ) {
                Text("Place My Order", color = RedPrimary, style = TitlePage, fontSize = 16.sp)
            }
        }
    }
}


@Composable
fun ProfileField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String
) {
    // State untuk menangani nilai sementara yang diubah oleh pengguna
    var temporaryValue by remember { mutableStateOf(value) } // Nilai sementara yang diubah oleh pengguna
    var isEditing by remember { mutableStateOf(false) } // Menentukan apakah TextField dalam mode edit atau tidak

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
            // Label (Text)
            Text(
                text = label,
                style = TitlePage,
                color = Color.Black,
                fontSize = 20.sp
            )

            // Input Field (TextField)
            OutlinedTextField(
                value = temporaryValue, // Menggunakan nilai sementara
                onValueChange = { newValue ->
                    if (isEditing) {
                        temporaryValue = newValue // Update nilai sementara jika dalam mode edit
                    }
                },
                enabled = isEditing, // Hanya bisa diubah jika mode edit
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically),
                placeholder = {
                    Text(
                        text = placeholder,
                        style = SubText,
                        color = Color.Gray,
                        fontSize = 20.sp
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.Black,
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White
                ),
                //contentPadding = PaddingValues(0.dp) // Menghilangkan padding default TextField
            )
            // Icon Edit
            IconButton(
                onClick = {
                    // Toggle status mode edit
                    isEditing = !isEditing

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