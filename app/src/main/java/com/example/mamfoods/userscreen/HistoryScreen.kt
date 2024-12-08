package com.example.mamfoods.userscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mamfoods.R
import com.example.mamfoods.ui.theme.GreenNeon
import com.example.mamfoods.ui.theme.SubText
import com.example.mamfoods.ui.theme.TitlePage
import com.example.mamfoods.ui.theme.White

@Composable
fun HistoryScreen(navController: NavController) {
    val selectedRoute = remember { mutableStateOf("history") }

    // State untuk status pesanan
    val orderStatus = remember { mutableStateOf(dummyOrderStatus) }

    Scaffold(
        bottomBar = {
            ButtonNavComponent(navController, selectedRoute)
        }
    ) { innerPadding ->
        Spacer(modifier = Modifier.height(50.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(innerPadding) // Gunakan innerPadding di sini
                .padding(horizontal = 20.dp) // Tambahan padding jika perlu
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            // Title
            Text(
                text = "History",
                style = TitlePage,
                fontSize = 32.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Call For Information Section
            if (orderStatus.value.isOrderInProgress) {
                // Card untuk informasi pemesanan
                Card(
                    shape = RoundedCornerShape(16.dp),
                    elevation = 4.dp,
                    backgroundColor = White,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column (
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(240.dp)
                            .padding(8.dp)
                            .paint(
                                painter = painterResource(R.drawable.pattern2), // Background PNG
                                contentScale = ContentScale.Crop
                            )
                    ) {
                        Text(
                            text = "Call For Information",
                            style = TitlePage,
                            fontSize = 20.sp,
                            modifier = Modifier
                                .padding(16.dp,12.dp, 16.dp, 16.dp)
                                .align(Alignment.Start)
                        )

                        Card (
                            shape = RoundedCornerShape(15.dp),
                            elevation = 2.dp,
                            backgroundColor = White,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(87.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start
                            ) {
                                Image(
                                    painter = painterResource(R.drawable.driver),
                                    contentDescription = "Driver",
                                    modifier = Modifier
                                        .size(70.dp)
                                        .clip(RoundedCornerShape(10.dp))
                                )

                                Spacer(modifier = Modifier.width(20.dp))

                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = orderStatus.value.driverName,
                                        style = TitlePage,
                                        fontSize = 16.sp
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                    // Waktu dan Ikon
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            painter = painterResource(R.drawable.map),
                                            contentDescription = "Clock Icon",
                                            tint = GreenNeon,
                                            modifier = Modifier.size(16.dp)
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            text = orderStatus.value.estimatedTime,
                                            style = SubText,
                                            fontSize = 14.sp,
                                            color = Color.LightGray
                                        )
                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        // Tombol Call
                        Button(
                            onClick = { /* Handle call action */ },
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .width(260.dp)
                                .height(68.dp),
                            shape = RoundedCornerShape(15.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.calllogo),
                                    contentDescription = "Call Icon",
                                    tint = GreenNeon,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(14.dp))
                                Text(
                                    text = "Call",
                                    style = TitlePage,
                                    fontSize = 16.sp,
                                    color = GreenNeon
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }


            Spacer(modifier = Modifier.height(24.dp))

            // List History Item
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(foodItems) { foodItem ->
                    HistoryItem(
                        foodItem = foodItem,
                        onBuyAgainClick = { /* Aksi tombol Buy Again */ }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun HistoryScreenPreview() {
    HistoryScreen(navController = rememberNavController())
}