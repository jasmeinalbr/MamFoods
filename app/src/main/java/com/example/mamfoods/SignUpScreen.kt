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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
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
import com.example.mamfoods.viewmodel.AuthViewModel

@Composable
fun SignUpScreen(

    onSignUpClick: () -> Unit,
    onFacebookSignUpClick: () -> Unit,
    onGoogleSignUpClick: () -> Unit,
    onLoginClick: () -> Unit,
    viewModel: AuthViewModel,
    onSignUpSuccess: () -> Unit

) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))

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
            style = SubText
        )
        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Sign Up Here",
            fontSize = 20.sp,
            style = SubText
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Name field
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = {
                Text(
                    text = "Name",
                    color = Color.LightGray,
                    fontSize = 14.sp,
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
                .height(57.dp),
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Email field
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
                .height(57.dp),
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password field
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
                .height(57.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Or sign-up section
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
            text = "Sign Up With",
            fontSize = 20.sp,
            color = Color.Black,
            fontFamily = YeonSung,
            textAlign = TextAlign.Center,
            letterSpacing = 1.5.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            // Facebook Sign Up Button
            Button(
                onClick = { onFacebookSignUpClick() },
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

                    Text(
                        text = "Facebook",
                        color = Color.Black,
                        fontFamily = Lato,
                        fontSize = 14.sp
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Google Sign Up Button
            Button(
                onClick = { onGoogleSignUpClick() },
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

                    Text(
                        text = "Google",
                        color = Color.Black,
                        fontFamily = Lato,
                        fontSize = 14.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Sign Up Button
        Button(

            onClick = {
                viewModel.register(
                    name = name,
                    email = email,
                    password = password,
                    onSuccess = {
                        onSignUpSuccess()
                    },
                    onError = { message ->
                        errorMessage = message
                    }
                )
            },

            modifier = Modifier
                .height(57.dp)
                .width(180.dp),
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(containerColor = RedPrimary)
        ) {
            Text(
                text = "Create Account",
                color = Color.White,
                fontFamily = YeonSung,
                fontSize = 20.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Login Link
        ClickableText(
            text = AnnotatedString("Already Have Account?"),
            onClick = { onLoginClick() },
            style = SubText.copy(color = LightGrayColor)
        )
    }

}

//@Preview(showBackground = true)
//@Composable
//fun PreviewSignup() {
//    SignUpScreen(
//        onSignUpClick = {}, // Event untuk SignUp
//        onFacebookSignUpClick = {}, // Event login Facebook
//        onGoogleSignUpClick = {},   // Event login Google
//        onLoginClick = {},
//        viewModel = AuthViewModel(),
//        onSignUpSuccess = {
//            navController.navigate("home")
//        }         // Event navigasi ke halaman Login
//    )
//}

