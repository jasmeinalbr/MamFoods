package com.example.mamfoods.userscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mamfoods.ui.theme.TitlePage

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
        BoxWithConstraints (
            modifier = Modifier.fillMaxSize().background(Color.White)
        ) {
            val screenWidth = maxWidth
            val screenHeight = maxHeight

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(innerPadding) // Gunakan innerPadding di sini
                    .padding(horizontal = 20.dp) // Tambahan padding jika perlu
            ) {
                Spacer(modifier = Modifier.height(screenHeight * 0.05f))

                // Title
                Text(
                    text = "History",
                    style = TitlePage,
                    fontSize = (screenWidth * 0.07f).value.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(screenHeight * 0.04f))

                if (orderStatus.value.isOrderInProgress) {
                    // Card hasil order
                    OrderProgressCard()
                    Spacer(modifier = Modifier.height(screenHeight * 0.02f))
                }

//                // List History Item
//                LazyColumn(modifier = Modifier.fillMaxSize()) {
//                    items(foodItems) { foodItem ->
//                        HistoryItem(
//                            foodItem = foodItem,
//                            onBuyAgainClick = { navController.navigate("cart") }
//                        )
//                    }
//                }
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun HistoryScreenPreview() {
    HistoryScreen(navController = rememberNavController())
}