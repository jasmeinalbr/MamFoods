package com.example.mamfoods.adminscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mamfoods.R

import com.example.mamfoods.ui.theme.SubText
import com.example.mamfoods.ui.theme.TitleText

@Composable

fun TampilanAdminActivity(onNavigateToOnboarding: () -> Unit) {

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
            text = "Admin Dasboard",
            style = SubText,
            fontSize = 14.sp
        )
    }

    androidx.compose.runtime.LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(3000) // Tunggu 3 detik
        onNavigateToOnboarding()
    }

}

@Preview(showBackground = true)
@Composable
fun AdminActivityPreview() {
    TampilanAdminActivity (onNavigateToOnboarding= {})
}
