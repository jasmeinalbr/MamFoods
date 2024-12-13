package com.example.mamfoods.userscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mamfoods.R
import com.example.mamfoods.ui.theme.DarkRed
import com.example.mamfoods.ui.theme.SubText
import com.example.mamfoods.ui.theme.TitlePage

@Composable
fun RestaurantDetailsScreen(navController: NavController, restaurant: Restaurant) {
    BoxWithConstraints (
        modifier = Modifier.fillMaxSize().background(Color.White)
    ) {
        val screenWidth = maxWidth
        val screenHeight = maxHeight

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(screenWidth * 0.08f),
        ) {
            // Back button
            IconButton(onClick = { navController.popBackStack() }) {
                Image(
                    painter = painterResource(id = R.drawable.backbutton),
                    contentDescription = "Back",
                    modifier = Modifier.size(screenWidth * 0.07f)
                )
            }
            Spacer(modifier = Modifier.height(screenHeight * 0.01f))

            // Title
            Text(
                text = restaurant.name,
                style = TitlePage,
                fontSize = (screenWidth * 0.08f).value.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.02f))

            // Image
            Image(
                painter = painterResource(id = restaurant.img),
                contentDescription = "Restaurant Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(screenHeight * 0.2f)
                    .clip(RoundedCornerShape(10.dp))
                    .align(Alignment.CenterHorizontally),
                contentScale = ContentScale.FillWidth
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.03f))

            // Description
            Text(
                text = "Description",
                style = TitlePage,
                fontSize = (screenWidth * 0.06f).value.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.02f))

            Text(
                text = restaurant.description,
                style = SubText,
                fontSize = (screenWidth * 0.035f).value.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.03f))

            // Ingredients
            Text(
                text = "Menu",
                style = TitlePage,
                fontSize = (screenWidth * 0.06f).value.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.02f))

            Column {
                restaurant.menu.forEach { foodItem ->
                    // Menu Row
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Nama makanan
                        Text(
                            text = "• ${foodItem.name}",
                            style = SubText,
                            color = Color.Black,
                            fontSize = (screenWidth * 0.04f).value.sp,
                            modifier = Modifier.weight(1f)
                        )

                        // "See Details" Text
                        Text(
                            text = "See Details",
                            style = SubText,
                            color = DarkRed,
                            fontSize = (screenWidth * 0.04f).value.sp,
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .clickable {
                                    navController.navigate("fooddetails/${foodItem.name}")
                                }
                        )

                        // Checkbox Icon
                        Image(
                            painter = painterResource(id = R.drawable.checkbox), // Ganti dengan ikon checkbox
                            contentDescription = "Checkbox",
                            modifier = Modifier.size(screenWidth * 0.04f)
                        )
                    }
                }
            }
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun PreviewRestaurantDetailsScreen() {
//    // Create a sample Restaurant and FoodItems for the preview
//    val sampleRestaurant = Restaurant(
//        name = "Best Pancake House",
//        description = "A cozy place to enjoy delicious pancakes with syrup and butter.",
//        img = R.drawable.toko,  // Replace with an actual drawable resource ID
//        menu = listOf(
//            FoodItem(
//                name = "Pancake",
//                restaurant = "Best Pancake House",
//                price = 25,
//                img = R.drawable.menu,  // Replace with an actual drawable resource ID
//                description = "Delicious pancake with syrup and butter.",
//                ingredients = listOf("Flour", "Egg", "Milk", "Syrup", "Butter")
//            ),
//            FoodItem(
//                name = "Waffles",
//                restaurant = "Best Pancake House",
//                price = 30,
//                img = R.drawable.menu,  // Replace with an actual drawable resource ID
//                description = "Crispy waffles served with fresh fruits and syrup.",
//                ingredients = listOf("Flour", "Egg", "Milk", "Syrup", "Fruits")
//            )
//        )
//    )
//
//    // Pass the sample Restaurant to the RestaurantDetailsScreen
//    RestaurantDetailsScreen(navController = rememberNavController(), restaurant = sampleRestaurant)
//}

