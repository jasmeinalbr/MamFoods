package com.example.mamfoods.userscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mamfoods.R
import com.example.mamfoods.ui.theme.DarkRed
import com.example.mamfoods.ui.theme.GreenNeon
import com.example.mamfoods.ui.theme.PriceText
import com.example.mamfoods.ui.theme.SubText
import com.example.mamfoods.ui.theme.TitlePage
import com.example.mamfoods.ui.theme.White

@Composable
fun OrderProgressCard(
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = 4.dp,
        backgroundColor = White,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(16.dp)
                .paint(
                    painter = painterResource(R.drawable.pattern2),
                    contentScale = ContentScale.Crop
                )
        ) {
            // Title
            Text(
                text = "Order Tracking",
                style = SubText,
                fontSize = 16.sp,
                color = Color.Black,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(16.dp))

            Card (
                shape = RoundedCornerShape(15.dp),
                elevation = 2.dp,
                backgroundColor = White,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(65.dp),
            ) {
                // Progress Bar
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Status Text
                    val statuses = listOf("Order Processed", "Shipped", "Order Delivered")
                    statuses.forEachIndexed { index, status ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.circle),
                                contentDescription = null,
                                modifier = Modifier.size(5.dp),
                                tint = if (index == 1) DarkRed else Color.Gray
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = status,
                                style = SubText,
                                fontSize = 14.sp,
                                color = if (index == 1) DarkRed else Color.Gray
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Order Details
            Column {
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {

                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Pizza",
                            style = TitlePage,
                            fontSize = 16.sp,
                            color = DarkRed
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = "Restaurant A",
                            style = SubText,
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }
                    Text(
                        text = "57,000",
                        style = PriceText,
                        fontSize = 18.sp,
                        color = DarkRed,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOrderProgressCard() {
    OrderProgressCard(
     
    )
}
