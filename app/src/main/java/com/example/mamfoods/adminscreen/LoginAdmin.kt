package com.example.mamfoods.adminscreen


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
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

// Temporary theme values for preview purposes
val TitleText = androidx.compose.ui.text.TextStyle(
    fontSize = 24.sp,
    fontWeight = FontWeight.Bold
)

val SubText = androidx.compose.ui.text.TextStyle(
    fontSize = 16.sp,
    fontWeight = FontWeight.Normal
)

val RedPrimary = Color(0xFFFF0000)
val LightGrayColor = Color(0xFFCCCCCC)

val YeonSung = androidx.compose.ui.text.font.FontFamily.Default

@Composable
fun LoginAdmin(
    onLoginSuccess: () -> Unit,
    onSignUpClick: () -> Unit
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


    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        val screenWidth = maxWidth
        val screenHeight = maxHeight

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = screenWidth * 0.05f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(screenWidth * 0.05f))

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo Mam Foods",
                modifier = Modifier.size(screenWidth * 0.3f)
            )
            Spacer(modifier = Modifier.height(screenHeight * 0.03f))

            Text(text = "Mam Foods", style = TitleText)
            Spacer(modifier = Modifier.height(screenHeight * 0.03f))
            Text(
                text = "Login To Your",
                fontSize = (screenWidth * 0.040f).value.sp,
                style = SubText,
                letterSpacing = 1.5.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Text(
                text = "Admin Dasboard",
                fontSize = (screenWidth * 0.040f).value.sp,
                style = SubText,
                letterSpacing = 1.5.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(screenHeight * 0.04f))

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
                    .height(screenHeight * 0.06f),
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.02f))

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
                    .height(screenHeight * 0.06f),
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.04f))

            Button(
                onClick = {
               isLoading = true
                authenticationManager.loginAdmin(email, password)
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
                Spacer(modifier = Modifier.height(screenHeight * 0.03f))
                Text(
                    text = errorMessage!!,
                    color = Color.Red,
                    fontSize = (screenWidth * 0.03f).value.sp,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(screenHeight * 0.02f))

            ClickableText(
                text = AnnotatedString("Don't Have Account?"),
                onClick = { onSignUpClick() },  // Navigate to sign up page
                style = SubText
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginAdmin() {
    LoginAdmin(
        onLoginSuccess = {},
        onSignUpClick = {}
    )
}


