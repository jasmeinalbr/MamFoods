package com.example.mamfoods

import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mamfoods.ui.theme.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

@Composable
fun LoginScreen(
    onLoginClick: () -> Unit,           // Event when login is successful (Navigates to home)
    onFacebookLoginClick: () -> Unit,    // Event for Facebook login
    onGoogleLoginClick: () -> Unit,      // Event for Google login
    onSignUpClick: () -> Unit            // Event for navigating to SignUp screen
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val auth = FirebaseAuth.getInstance()
    var errorMessage by remember { mutableStateOf("") }

    // Sign Up Logic
    fun performSignUp() {
        if (email.isEmpty() || password.isEmpty()) {
            errorMessage = "Please fill in both fields"
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onLoginClick()  // Navigate to home after sign up
                } else {
                    errorMessage = task.exception?.message ?: "Sign Up failed"
                }
            }
    }

    // Login Logic
    fun performLogin() {
        if (email.isEmpty() || password.isEmpty()) {
            errorMessage = "Please fill in both fields"
            return
        }

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onLoginClick()  // Navigate to home after successful login
                } else {
                    errorMessage = task.exception?.message ?: "Login failed"
                }
            }
    }

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
        Text(
            text = "Mam Foods",
            style = TitleText
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Deliver Favorite Food & Drink",
            fontSize = 14.sp,
            style = SubText,
            letterSpacing = 1.5.sp
        )
        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Login To Your Account",
            fontSize = 20.sp,
            style = SubText
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = {
                Text(
                    text = "Email or Phone Number",
                    color = Color.LightGray,
                    fontSize = 14.sp,
                    fontFamily = Lato,
                    letterSpacing = 1.5.sp,
                )
            },
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
                    text = "Password",
                    color = Color.LightGray,
                    fontSize = 14.sp,
                    fontFamily = Lato,
                    letterSpacing = 1.5.sp
                )
            },
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
                .height(57.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(8.dp)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { performLogin() },  // Login action
            modifier = Modifier.height(57.dp).width(157.dp),
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(containerColor = RedPrimary)
        ) {
            Text(
                text = "Login",
                color = Color.White,
                fontFamily = YeonSung,
                fontSize = 20.sp
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