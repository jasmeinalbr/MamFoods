package com.example.mamfoods

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mamfoods.ui.theme.SubText
import com.example.mamfoods.ui.theme.TitleText
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    // Displaying the splash screen with logo and text
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "Logo Mam Foods",
            modifier = Modifier.size(200.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Mam Foods",
            style = TitleText
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Deliver Favorite Food & Drink",
            style = SubText,
            fontSize = 14.sp
        )
    }

    // Navigate to the next screen after a delay of 3 seconds
    LaunchedEffect(Unit) {
        delay(3000) // Wait for 3 seconds
        navController.navigate("signup") {
            popUpTo("splash") { inclusive = true }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    val navController = rememberNavController()
    SplashScreen(navController)
}