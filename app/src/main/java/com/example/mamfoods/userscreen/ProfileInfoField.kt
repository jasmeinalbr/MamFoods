package com.example.mamfoods.userscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mamfoods.R
import com.example.mamfoods.ui.theme.Lato
import com.example.mamfoods.ui.theme.SubText
import com.example.mamfoods.ui.theme.TitlePage

@Composable
fun ProfileInfoField(
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
                        .align(Alignment.CenterVertically)
                        .padding(0.dp),
                    textStyle = TextStyle(
                        fontFamily = Lato,
                        fontSize = 16.sp
                    ),
                    placeholder = { // Placeholder menggunakan lambda
                        Text(
                            text = placeholder,
                            style = SubText,
                            color = Color.Gray,
                            fontSize = 16.sp
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
                    textStyle = TextStyle(
                        fontFamily = Lato,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp, // Ukuran font untuk value
                    ),
                    placeholder = {
                        Text(
                            text = placeholder,
                            style = SubText,
                            color = Color.Gray,
                            fontSize = 16.sp
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