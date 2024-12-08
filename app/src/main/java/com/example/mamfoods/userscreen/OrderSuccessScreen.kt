package com.example.mamfoods.userscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mamfoods.R
import com.example.mamfoods.ui.theme.RedPrimary
import com.example.mamfoods.ui.theme.YeonSung

@Composable
fun OrderSuccessScreen(navController: NavController) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Congrats",
            color = RedPrimary,
            fontSize = 32.sp,
            fontFamily = YeonSung,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Your Order Placed",
            color = RedPrimary,
            fontSize = 32.sp,
            fontFamily = YeonSung,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(30.dp))
        Image(
            painter = painterResource(R.drawable.success),
            contentDescription = "Food Illustration",
            modifier = Modifier.size(165.dp)
        )
        Spacer(modifier = Modifier.height(50.dp))
        Button(
            onClick = { navController.navigate("home") },
            colors = ButtonDefaults.buttonColors(
                containerColor = RedPrimary
            ),
            modifier = Modifier
                .height(57.dp)
                .width(157.dp),
            shape = RoundedCornerShape(15.dp)
        ) {
            Text(
                text = "Go Gome",
                color = Color.White,
                fontFamily = YeonSung,
                fontSize = 16.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrderSuccessScreenPreview() {
    OrderSuccessScreen(navController = rememberNavController())
}