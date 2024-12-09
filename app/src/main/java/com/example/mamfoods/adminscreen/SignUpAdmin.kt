package com.example.mamfoods.adminscreen

import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
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

@Composable
fun SignUpAdmin(
    onSignUpClick: () -> Unit,
    onFacebookSignUpClick: () -> Unit,
    onGoogleSignUpClick: () -> Unit,
    onLoginClick: () -> Unit,
    // viewModel: AuthViewModel,
    // onSignUpSuccess: () -> Unit
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
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Mam Foods",
            textAlign = TextAlign.Center,
            style = TitleText
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Sign Up Here For You",
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            style = SubText
        )
        Text(
            text = "Admin Dasboard",
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            style = SubText
        )
        Spacer(modifier = Modifier.height(15.dp))
        androidx.compose.material.Text(
            text = "Choose Your Location",
            fontFamily = YeonSung,
            fontSize = 14.sp,
            color = RedPrimary,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(top = 16.dp)
                .padding(bottom = 16.dp)
                .align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(2.dp))

        // Dropdown
        var expanded by remember { mutableStateOf(false) }
        var selectedLocation by remember { mutableStateOf("Cibiru") }
        val locations = listOf("Cibiru", "Bandung", "Jakarta", "Surabaya")

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(8.dp))
                .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .clickable { expanded = !expanded },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                androidx.compose.material.Text(
                    text = selectedLocation,
                    color = Color.Black,
                    fontFamily = Lato
                )
                Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Dropdown Icon")
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                locations.forEach { location ->
                    DropdownMenuItem(onClick = {
                        selectedLocation = location
                        expanded = false
                    }) {
                        androidx.compose.material.Text(text = location)
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))

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

        Spacer(modifier = Modifier.height(8.dp))

        // Name Restaurant field
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = {
                Text(
                    text = "Name Of Restaurant",
                    color = Color.LightGray,
                    fontSize = 14.sp,
                    fontFamily = Lato,
                    letterSpacing = 1.5.sp,
                )
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.home),
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

        Spacer(modifier = Modifier.height(8.dp))

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

        Spacer(modifier = Modifier.height(8.dp))

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

        Spacer(modifier = Modifier.height(8.dp))

        // Sign Up Button
        Button(
            onClick = {
                // viewModel.register(
                // name = name,
                // email = email,
                // password = password,
                // onSuccess = {
                // onSignUpSuccess()
                // },
                // onError = { message ->
                // errorMessage = message
                // }
                // )
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

        Spacer(modifier = Modifier.height(8.dp))

        // Text "Already have an account?"
        ClickableText(
            text = AnnotatedString("Already Have An Account?"),
            onClick = { onLoginClick() },
            style = androidx.compose.ui.text.TextStyle(
                color = RedPrimary,
                fontSize = 10.sp,
                fontFamily = Lato,
                letterSpacing = 1.5.sp
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSignup() {
    SignUpAdmin(
        onSignUpClick = {},
        onFacebookSignUpClick = {},
        onGoogleSignUpClick = {},
        onLoginClick = {}
    )
}

