package com.example.mamfoods.userscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.app.ui.components.AutoSlidingBanner
import com.example.mamfoods.R
import com.example.mamfoods.ui.theme.BodyText
import com.example.mamfoods.ui.theme.DetailText
import com.example.mamfoods.ui.theme.Lato
import com.example.mamfoods.ui.theme.LightGrayColor
import com.example.mamfoods.ui.theme.OffRed
import com.example.mamfoods.ui.theme.OffRed2
import com.example.mamfoods.ui.theme.PriceText
import com.example.mamfoods.ui.theme.RedPrimary
import com.example.mamfoods.ui.theme.SubText
import com.example.mamfoods.ui.theme.TitlePage
import com.example.mamfoods.BottomNavItem
import com.example.mamfoods.ui.theme.White

@Composable
fun FoodAppHomeScreen(
    navController: NavController,
    onViewMoreClick: () -> Unit = { }
) {
    val selectedRoute = remember { mutableStateOf("home") }

    val bannerImages = listOf(
        painterResource(id = R.drawable.banner1),
        painterResource(id = R.drawable.banner1),
        painterResource(id = R.drawable.banner1)
    )

    Scaffold(
        bottomBar = {
            CustomBottomNavigation(navController, selectedRoute)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(innerPadding) // Gunakan innerPadding di sini
                .padding(horizontal = 20.dp) // Tambahan padding jika perlu
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Header
            Text(
                text = "Explore Your Favorite Food",
                style = TitlePage,
                color = RedPrimary,
                modifier = Modifier.padding(vertical = 24.dp)
            )

            // Search Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = OffRed, shape = RoundedCornerShape(15.dp))
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.search),
                    contentDescription = "Icon Search",
                    modifier = Modifier.size(30.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "What do you want to order?",
                    color = OffRed2,
                    fontFamily = Lato,
                    fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Banner
            AutoSlidingBanner(images = bannerImages)

            Spacer(modifier = Modifier.height(16.dp))

            // Popular Section
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Popular",
                    style = BodyText,
                    color = RedPrimary,
                    fontSize = 24.sp
                )
                ClickableText(
                    text = AnnotatedString("View More"),
                    onClick = { onViewMoreClick() },
                    style = SubText
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Popular Items
            LazyColumn {
                items(popularItems) { item ->
                    FoodItemCard(item)
                }
            }
        }

        when (selectedRoute.value) {
            "cart" -> CartScreen()
            "history" -> HistoryScreen()
            "profile" -> ProfileScreen()
        }
    }
}


@Composable
fun FoodItemCard(item: FoodItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp)
            .padding(vertical = 8.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(LightGrayColor)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        // Image
        Box(
            modifier = Modifier
                .size(65.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.LightGray)
        ) {
            // Replace with Image loading logic
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Texts
        Column(modifier = Modifier.weight(1f)) {
            Text(text = item.name, style = BodyText)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = item.restaurant,
                style = DetailText,
                color = Color.Gray
            )
        }

        // Price
        Text(
            text = "Rp. ${item.price}",
            style = PriceText,
        )
    }
}

// Dummy Data
data class FoodItem(val name: String, val restaurant: String, val price: Int)

val popularItems = listOf(
    FoodItem("Pancake", "Cake Castle", 20000),
    FoodItem("Matcha latte", "Wijie Resto", 15000),
    FoodItem("Mie Hijau", "Warung Mie", 25000),
    FoodItem("Mie Hijau", "Warung Mie", 25000),
    FoodItem("Mie Hijau", "Warung Mie", 25000),
    FoodItem("Mie Hijau", "Warung Mie", 25000),
    FoodItem("Mie Hijau", "Warung Mie", 25000)
)

@Preview(showBackground = true, apiLevel = 34)
@Composable
fun PreviewFoodAppHomeScreen() {
    FoodAppHomeScreen(navController = rememberNavController())
}