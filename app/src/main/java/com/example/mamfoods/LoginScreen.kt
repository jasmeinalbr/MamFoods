package com.example.mamfoods

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mamfoods.ui.theme.*
import com.example.mamfoods.viewmodel.AuthViewModel

@Composable
fun LoginScreen(
    viewModel: AuthViewModel,
    onLoginSuccess: () -> Unit,
    onSignUpClick: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo Mam Foods",
            modifier = Modifier.size(100.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Mam Foods", style = TitleText)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Deliver Favorite Food & Drink",
            fontSize = 14.sp,
            style = SubText,
            letterSpacing = 1.5.sp
        )
        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email or Phone Number", style = SubText) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.Black,
                focusedBorderColor = LightGrayColor,
                unfocusedBorderColor = LightGrayColor
            ),
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(57.dp),
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password", style = SubText) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.Black,
                focusedBorderColor = LightGrayColor,
                unfocusedBorderColor = LightGrayColor
            ),
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(57.dp),
        )

        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = {
                isLoading = true
                errorMessage = null
                viewModel.login(
                    email = email,
                    password = password,
                    onSuccess = {
                        isLoading = false
                        onLoginSuccess()
                    },
                    onError = {
                        isLoading = false
                        errorMessage = it
                    }
                )
            },
            modifier = Modifier
                .height(57.dp)
                .width(157.dp),
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(containerColor = RedPrimary)
        ) {
            Text(
                text = if (isLoading) "Loading..." else "Login",
                color = Color.White,
                fontFamily = YeonSung,
                fontSize = 20.sp
            )
        }

        if (errorMessage != null) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = errorMessage!!,
                color = Color.Red,
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        ClickableText(
            text = AnnotatedString("Don't Have Account?"),
            onClick = { onSignUpClick() },
            style = SubText
        )
    }

}


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    // Buat dummy ViewModel untuk kebutuhan Preview
    val dummyViewModel = object : AuthViewModel() {
        override fun login(
            email: String,
            password: String,
            onSuccess: () -> Unit,
            onError: (String) -> Unit
        ) {
            // Simulasi login berhasil atau gagal
            if (email == "user@example.com" && password == "password") {
                onSuccess()
            } else {
                onError("Invalid credentials")
            }
        }
    }

    // Preview UI
    LoginScreen(
        viewModel = dummyViewModel,
        onLoginSuccess = {
            // Simulasi sukses login
        },
        onSignUpClick = {
            // Simulasi navigasi ke halaman registrasi
        }
    )
}

