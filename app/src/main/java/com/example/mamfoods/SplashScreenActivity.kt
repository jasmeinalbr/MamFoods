package com.example.mamfoods

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import com.example.mamfoods.ui.theme.Lato
import com.example.mamfoods.ui.theme.RedGradient1
import com.example.mamfoods.ui.theme.RedGradient2
import com.example.mamfoods.ui.theme.RedPrimary
import com.example.mamfoods.ui.theme.YeonSung

@Composable
fun SplashScreen() {
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
        Spacer(modifier = Modifier.height(24.dp)) // Memberikan jarak antar elemen
        Text(
            text = "Mam Foods",
            fontSize = 40.sp,
            fontFamily = YeonSung,
            color = RedPrimary
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Deliver Favorite Food & Drink",
            color = RedPrimary,
            fontSize = 14.sp,
            fontFamily = Lato,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen()
}

