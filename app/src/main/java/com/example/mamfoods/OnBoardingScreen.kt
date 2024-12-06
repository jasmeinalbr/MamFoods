package com.example.mamfoods

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.mamfoods.ui.theme.RedPrimary
import com.example.mamfoods.ui.theme.YeonSung

@Composable
fun OnboardingScreen(onNextClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.logostart),
            contentDescription = "Food Illustration",
            modifier = Modifier.size(300.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Enjoy Restaurant Quality Meals\nat Home",
            color = RedPrimary,
            fontSize = 18.sp,
            fontFamily = YeonSung,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(80.dp))
        Button(
            onClick = onNextClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = RedPrimary
            ),
            modifier = Modifier.height(57.dp).width(157.dp),
            shape = RoundedCornerShape(15.dp)
        ) {
            Text(
                text = "Next",
                color = Color.White,
                fontFamily = YeonSung,
                fontSize = 20.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    OnboardingScreen(onNextClick = {})
}
