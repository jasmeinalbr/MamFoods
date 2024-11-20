package com.example.mamfoods

import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mamfoods.ui.theme.Lato
import com.example.mamfoods.ui.theme.LightGrayColor
import com.example.mamfoods.ui.theme.RedPrimary
import com.example.mamfoods.ui.theme.SubText
import com.example.mamfoods.ui.theme.TitleText
import com.example.mamfoods.ui.theme.YeonSung

@Composable
fun LoginScreen(
    onLoginClick: () -> Unit, // Event untuk login
    onFacebookLoginClick: () -> Unit, // Event login Facebook
    onGoogleLoginClick: () -> Unit,   // Event login Google
    onSignUpClick: () -> Unit         // Event navigasi ke halaman Sign Up
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

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

        // Password Input
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
        Text(
            text = "Or",
            fontSize = 10.sp,
            color = Color.Black,
            fontFamily = YeonSung,
            textAlign = TextAlign.Center,
            letterSpacing = 1.5.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Continue With",
            fontSize = 20.sp,
            color = Color.Black,
            fontFamily = YeonSung,
            textAlign = TextAlign.Center,
            letterSpacing = 1.5.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { onFacebookLoginClick() },
            modifier = Modifier.height(57.dp).width(157.dp),
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            border = BorderStroke(1.dp, LightGrayColor)
        ) {
            Row (verticalAlignment = Alignment.CenterVertically){
                Image(
                    painter = painterResource(id = R.drawable.fb),
                    contentDescription = "facebook",
                    modifier = Modifier.size(25.dp).padding(end = 8.dp))
                Text(
                    text = "Facebook",
                    color = Color.Black,
                    fontFamily = Lato,
                    fontSize = 14.sp)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { onGoogleLoginClick() },
            modifier = Modifier.height(57.dp).width(157.dp),
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            border = BorderStroke(1.dp, LightGrayColor)
        ) {
            Row (verticalAlignment = Alignment.CenterVertically){
                Image(
                    painter = painterResource(id = R.drawable.google),
                    contentDescription = "google",
                    modifier = Modifier.size(25.dp).padding(end = 8.dp))
                Text(
                    text = "Google",
                    color = Color.Black,
                    fontFamily = Lato,
                    fontSize = 14.sp)
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = { onLoginClick() },
            modifier = Modifier.height(57.dp).width(157.dp),
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(containerColor = RedPrimary)
        ) {
            Text(
                text = "Login",
                color = Color.White,
                fontFamily = YeonSung,
                fontSize = 20.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Sign Up Link
        ClickableText(
            text = AnnotatedString("Don't Have Account?"),
            onClick = { onSignUpClick() },
            style = SubText
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    LoginScreen(onLoginClick = {}, // Event untuk login
    onFacebookLoginClick = {}, // Event login Facebook
    onGoogleLoginClick = {},   // Event login Google
    onSignUpClick = {}         // Event navigasi ke halaman Sign Up
    )
}