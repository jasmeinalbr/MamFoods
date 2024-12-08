package com.example.mamfoods.userscreen


import androidx.compose.foundation.BorderStroke
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
import com.example.mamfoods.R
import com.example.mamfoods.ui.theme.*
import com.example.mamfoods.viewmodel.AuthViewModel

@Composable
fun LoginScreen(
    viewModel: AuthViewModel,
    onLoginSuccess: () -> Unit,
    onSignUpClick: () -> Unit,
    onFacebookLoginClick: () -> Unit,
    onGoogleLoginClick:() -> Unit
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
        Spacer(modifier = Modifier.height(28.dp))

        Text(
            text = "Login To Your Account",
            fontSize = 24.sp,
            style = SubText,
            letterSpacing = 1.5.sp
        )
        Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = {
                    Text(
                        "Email or Phone Number",
                        style = SubText,
                        color = Color.LightGray,
                        letterSpacing = 1.5.sp
                    ) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.Black,
                    focusedBorderColor = LightGrayColor,
                    unfocusedBorderColor = LightGrayColor,
                    backgroundColor = Color.White,
                    cursorColor = Color.Black
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
                label = {
                    Text(
                        "Password",
                        style = SubText,
                        color = Color.LightGray,
                        letterSpacing = 1.5.sp
                    ) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.Black,
                    focusedBorderColor = LightGrayColor,
                    unfocusedBorderColor = LightGrayColor,
                    backgroundColor = Color.White,
                    cursorColor = Color.Black
                ),
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(57.dp),
            )

        Spacer(modifier = Modifier.height(24.dp))

        // Or sign-up section
        Text(
            text = "Or",
            fontSize = 12.sp,
            color = Color.Black,
            fontFamily = YeonSung,
            textAlign = TextAlign.Center,
            letterSpacing = 1.5.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Continue With",
            fontSize = 24.sp,
            color = Color.Black,
            fontFamily = YeonSung,
            textAlign = TextAlign.Center,
            letterSpacing = 1.5.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Facebook Login Button
        Button(
            onClick = { onFacebookLoginClick() },
            modifier = Modifier
                .height(57.dp)
                .width(157.dp),
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            border = BorderStroke(1.dp, LightGrayColor)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.fb),

                    contentDescription = "facebook",
                    modifier = Modifier
                        .size(25.dp)
                        .padding(end = 8.dp))

                androidx.compose.material3.Text(
                    text = "Facebook",
                    color = Color.Black,
                    fontFamily = Lato,
                    fontSize = 14.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Google Login Button
        Button(
            onClick = { onGoogleLoginClick() },
            modifier = Modifier
                .height(57.dp)
                .width(157.dp),
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            border = BorderStroke(1.dp, LightGrayColor)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.google),

                    contentDescription = "google",
                    modifier = Modifier
                        .size(25.dp)
                        .padding(end = 8.dp))

                androidx.compose.material3.Text(
                    text = "Google",
                    color = Color.Black,
                    fontFamily = Lato,
                    fontSize = 14.sp
                )
            }
        }

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
            onClick = { onSignUpClick() },  // Navigate to sign up page
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
        },
        onGoogleLoginClick = {},
        onFacebookLoginClick = {}
    )
}


