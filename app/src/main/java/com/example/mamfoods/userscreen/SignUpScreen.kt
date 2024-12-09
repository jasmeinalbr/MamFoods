package com.example.mamfoods.userscreen

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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mamfoods.R
import com.example.mamfoods.ui.theme.Lato
import com.example.mamfoods.ui.theme.LightGrayColor
import com.example.mamfoods.ui.theme.RedPrimary
import com.example.mamfoods.ui.theme.SubText
import com.example.mamfoods.ui.theme.TitleText
import com.example.mamfoods.ui.theme.YeonSung
import com.example.mamfoods.viewmodel.AuthViewModel

@Composable
fun SignUpScreen(
    onFacebookSignUpClick: () -> Unit,
    onGoogleSignUpClick: () -> Unit,
    onLoginClick: () -> Unit,
//    viewModel: AuthViewModel,
    onSignUpSuccess: () -> Unit

) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    BoxWithConstraints (
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
            Spacer(modifier = Modifier.height(screenHeight * 0.05f))

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo Mam Foods",
                modifier = Modifier.size(screenWidth * 0.25f)
            )
            Spacer(modifier = Modifier.height(screenHeight * 0.01f))
            Text(
                text = "Mam Foods",
                style = TitleText,
                fontSize = (screenWidth * 0.08f).value.sp
            )
            Spacer(modifier = Modifier.height(screenHeight * 0.01f))
            Text(
                text = "Deliver Favorite Food & Drink",
                style = SubText,
                fontSize = (screenWidth * 0.03f).value.sp
            )
            Spacer(modifier = Modifier.height(screenHeight * 0.03f))

            Text(
                text = "Sign Up Here",
                fontSize = (screenWidth * 0.045f).value.sp,
                style = SubText
            )
            Spacer(modifier = Modifier.height(screenHeight * 0.03f))

            // Name field
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = {
                    Text(
                        text = "Name",
                        color = Color.LightGray,
                        fontSize = (screenWidth * 0.035f).value.sp,
                        fontFamily = Lato,
                        letterSpacing = 1.5.sp,
                    )
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.user),
                        contentDescription = "User Icon",
                        tint = Color.Black,
                        modifier = Modifier.size(24.dp)
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
                    .height(screenHeight * 0.07f),
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.01f))

            // Email field
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = {
                    Text(
                        text = "Email or Phone Number",
                        color = Color.LightGray,
                        fontSize = (screenWidth * 0.035f).value.sp,
                        fontFamily = Lato,
                        letterSpacing = 1.5.sp,
                    )
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.mail),
                        contentDescription = "Mail Icon",
                        tint = Color.Black,
                        modifier = Modifier.size(24.dp)
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
                    .height(screenHeight * 0.07f),
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.01f))

            // Password field
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = {
                    Text(
                        text = "Password",
                        color = Color.LightGray,
                        fontSize = (screenWidth * 0.035f).value.sp,
                        fontFamily = Lato,
                        letterSpacing = 1.5.sp
                    )
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.lock),
                        contentDescription = "Lock Icon",
                        tint = Color.Black,
                        modifier = Modifier.size(24.dp)
                    )
                },
                trailingIcon = {
                    val icon = if (passwordVisible) R.drawable.eye else R.drawable.eyeoff
                    val description = if (passwordVisible) "Hide password" else "Show password"
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            painter = painterResource(id = icon),
                            contentDescription = description,
                            tint = Color.Black,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
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

            // Or sign-up section
            Text(
                text = "Or",
                fontSize = (screenWidth * 0.03f).value.sp,
                color = Color.Black,
                fontFamily = YeonSung,
                textAlign = TextAlign.Center,
                letterSpacing = 1.5.sp
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.01f))

            Text(
                text = "Sign Up With",
                fontSize = (screenWidth * 0.05f).value.sp,
                color = Color.Black,
                fontFamily = YeonSung,
                textAlign = TextAlign.Center,
                letterSpacing = 1.5.sp
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.03f))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Facebook Sign Up Button
                Button(
                    onClick = { onFacebookSignUpClick() },
                    modifier = Modifier
                        .width(screenWidth * 0.4f)
                        .height(screenHeight * 0.07f),
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

                        Text(
                            text = "Facebook",
                            color = Color.Black,
                            fontFamily = Lato,
                            fontSize = (screenWidth * 0.035f).value.sp,
                        )
                    }
                }

                Spacer(modifier = Modifier.width(screenWidth * 0.02f))

                // Google Sign Up Button
                Button(
                    onClick = { onGoogleSignUpClick() },
                    modifier = Modifier
                        .width(screenWidth * 0.4f)
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

                        Text(
                            text = "Google",
                            color = Color.Black,
                            fontFamily = Lato,
                            fontSize = (screenWidth * 0.035f).value.sp,
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(screenHeight * 0.03f))

            // Sign Up Button
            Button(
                onClick = {
//                viewModel.register(
//                    name = name,
//                    email = email,
//                    password = password,
//                    onSuccess = {
//                        onSignUpSuccess()
//                    },
//                    onError = { message ->
//                        errorMessage = message
//                    }
//                )
                },

                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(screenHeight * 0.07f),
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(containerColor = RedPrimary)
            ) {
                Text(
                    text = "Create Account",
                    color = Color.White,
                    fontFamily = YeonSung,
                    fontSize = (screenWidth * 0.05f).value.sp,
                )
            }

            Spacer(modifier = Modifier.height(screenHeight * 0.01f))

            // Login Link
            ClickableText(
                text = AnnotatedString("Already Have Account?"),
                onClick = { onLoginClick() },
                style = SubText
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSignup() {
    SignUpScreen(
        onFacebookSignUpClick = {}, // Event login Facebook
        onGoogleSignUpClick = {},   // Event login Google
        onLoginClick = {},
//        viewModel = AuthViewModel(),
        onSignUpSuccess = {}
    )
}

