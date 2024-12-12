package com.example.mamfoods.userscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.mamfoods.R
import com.example.mamfoods.ui.theme.RedPrimary
import com.example.mamfoods.ui.theme.YeonSung

@Composable
fun OnboardingScreen(onAdminClick: () -> Unit, onUserClick: () -> Unit) {

    BoxWithConstraints (
        modifier = Modifier.fillMaxSize().background(Color.White)
    ) {
        val screenWidth = maxWidth
        val screenHeight = maxHeight

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(screenWidth * 0.05f)
                .background(Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.logostart),
                contentDescription = "Food Illustration",
                modifier = Modifier.size(screenWidth * 0.8f)
            )
            Spacer(modifier = Modifier.height(screenHeight * 0.02f))
            Text(
                text = "Enjoy Restaurant Quality Meals\nat Home",
                color = RedPrimary,
                fontSize = (screenWidth * 0.05f).value.sp,
                fontFamily = YeonSung,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(screenHeight * 0.08f))

            Row (
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = onAdminClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = RedPrimary
                    ),
                    modifier = Modifier
                        .width(screenWidth * 0.4f)
                        .height(screenHeight * 0.07f),
                    shape = RoundedCornerShape(15.dp)
                ) {
                    Text(
                        text = "Admin",
                        color = Color.White,
                        fontFamily = YeonSung,
                        fontSize = (screenWidth * 0.05f).value.sp
                    )
                }

                Spacer(modifier = Modifier.width(screenWidth * 0.04f))

                Button(
                    onClick = onUserClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = RedPrimary
                    ),
                    modifier = Modifier
                        .width(screenWidth * 0.4f)
                        .height(screenHeight * 0.07f),
                    shape = RoundedCornerShape(15.dp)
                ) {
                    Text(
                        text = "User",
                        color = Color.White,
                        fontFamily = YeonSung,
                        fontSize = (screenWidth * 0.05f).value.sp
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    OnboardingScreen(onAdminClick = {}, onUserClick = {})
}
