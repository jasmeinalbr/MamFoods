package com.example.mamfoods

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mamfoods.ui.theme.SubText
import com.example.mamfoods.ui.theme.TitleText
import kotlinx.coroutines.delay

@Composable
fun TampilanAdminActivity(onNavigateToOnboarding: () -> Unit) {

    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        val screenWidth = maxWidth
        val screenHeight = maxHeight

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = screenWidth * 0.05f)
                .background(Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "Logo Mam Foods",
                modifier = Modifier.size(screenWidth * 0.50f)
            )
            Spacer(modifier = Modifier.height(screenHeight * 0.03f))
            Text(
                text = "Mam Foods",
                style = TitleText
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Admin Dasboard",
                style = SubText,
                fontSize = (screenWidth * 0.040f).value.sp
            )
        }

        LaunchedEffect(Unit) {
            delay(3000) // Tunggu 3 detik
            onNavigateToOnboarding()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AdminActivityPreview() {
    TampilanAdminActivity(onNavigateToOnboarding = {})
}
