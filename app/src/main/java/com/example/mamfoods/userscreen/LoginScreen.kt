package com.example.mamfoods.userscreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mamfoods.AuthResponse
import com.example.mamfoods.AuthenticationManager
import com.example.mamfoods.R
import com.example.mamfoods.ui.theme.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onSignUpClick: () -> Unit,
    onFacebookLoginClick: () -> Unit,
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val context = LocalContext.current
    val  authenticationManager = remember {
        AuthenticationManager(context)
    }
    val coroutineScope = rememberCoroutineScope()

    BoxWithConstraints (
        modifier = Modifier.fillMaxSize().background(Color.White)
    ) {
        val screenWidth = maxWidth
        val screenHeight = maxHeight

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(screenWidth * 0.05f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(screenHeight * 0.05f))

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo Mam Foods",
                modifier = Modifier.size(screenWidth * 0.3f)
            )
            Spacer(modifier = Modifier.height(screenHeight * 0.02f))
            Text(
                text = "Mam Foods",
                style = TitleText,
                fontSize = (screenWidth * 0.08f).value.sp
            )
            Spacer(modifier = Modifier.height(screenHeight * 0.01f))
            Text(
                text = "Deliver Favorite Food & Drink",
                fontSize = (screenWidth * 0.03f).value.sp,
                style = SubText,
                letterSpacing = 1.5.sp
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.03f))

            Text(
                text = "Login To Your Account",
                fontSize = (screenWidth * 0.045f).value.sp,
                style = SubText,
                letterSpacing = 1.5.sp
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.03f))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = {
                    Text(
                        "Email or Phone Number",
                        style = SubText,
                        fontSize = (screenWidth * 0.035f).value.sp,
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
                    .height(screenHeight * 0.07f),
            )
            Spacer(modifier = Modifier.height(screenHeight * 0.02f))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = {
                    Text(
                        "Password",
                        style = SubText,
                        fontSize = (screenWidth * 0.035f).value.sp,
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

            Spacer(modifier = Modifier.height(screenHeight * 0.04f))

            // Or sign-up section
            Text(
                text = "Or",
                fontSize = (screenWidth * 0.03f).value.sp,
                color = Color.Black,
                fontFamily = YeonSung,
                textAlign = TextAlign.Center,
                letterSpacing = 1.5.sp
            )
            Spacer(modifier = Modifier.height(screenHeight * 0.003f))

            Text(
                text = "Continue With",
                fontSize = (screenWidth * 0.05f).value.sp,
                color = Color.Black,
                fontFamily = YeonSung,
                textAlign = TextAlign.Center,
                letterSpacing = 1.5.sp
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.02f))

            // Google Login Button
            Button(
                onClick = {
                    authenticationManager.signInWithGoogle()
                        .onEach {response->
                            if(response is AuthResponse.Success){
                                onLoginSuccess()

                            }else if(response is AuthResponse.Error){
                                errorMessage = response.message
                            }
                        }
                        .launchIn(coroutineScope)
                },
                modifier = Modifier
                    .fillMaxWidth(0.45f)
                    .height(screenHeight * 0.07f),
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
                        fontSize = (screenWidth * 0.035f).value.sp,
                    )
                }
            }

            Spacer(modifier = Modifier.height(screenHeight * 0.04f))

        Button(
            onClick = {
                isLoading = true
                errorMessage = null
                authenticationManager.loginWithEmail(email, password)
                    .onEach {response->
                        if(response is AuthResponse.Success){
                           onLoginSuccess()
                        }
                    }
                    .launchIn(coroutineScope)
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
                    fontSize = (screenWidth * 0.05f).value.sp,
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

            Spacer(modifier = Modifier.height(screenHeight * 0.01f))

            ClickableText(
                text = AnnotatedString("Don't Have Account?"),
                onClick = { onSignUpClick() },  // Navigate to sign up page
                style = SubText,
            )
        }
    }


}

//
//@Preview(showBackground = true)
//@Composable
//fun LoginScreenPreview() {
//    // Buat dummy ViewModel untuk kebutuhan Preview
//    val dummyViewModel = object : AuthViewModel() {
//        override fun login(
//            request: LoginRequest,
//            onSuccess: () -> Unit,
//            onError: (String) -> Unit
//        ) {
//            // Simulasi login berhasil atau gagal
//            if (request.email == "user@example.com" && request.password == "password") {
//                onSuccess()
//            } else {
//                onError("Invalid credentials")
//            }
//        }
//    }
//
//    // Preview UI
//    LoginScreen(
//        viewModel = dummyViewModel,
//        onLoginSuccess = {
//            // Simulasi sukses login
//        },
//        onSignUpClick = {
//            // Simulasi navigasi ke halaman registrasi
//        },
//        onGoogleLoginClick = {},
//        onFacebookLoginClick = {}
//    )
//}


