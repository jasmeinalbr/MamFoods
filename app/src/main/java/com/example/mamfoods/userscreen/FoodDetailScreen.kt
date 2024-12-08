package com.example.mamfoods.userscreen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mamfoods.R
import com.example.mamfoods.ui.theme.BodyText
import com.example.mamfoods.ui.theme.DarkRed
import com.example.mamfoods.ui.theme.DetailText
import com.example.mamfoods.ui.theme.RedPrimary
import com.example.mamfoods.ui.theme.SubText
import com.example.mamfoods.ui.theme.TitlePage
import com.example.mamfoods.ui.theme.TitleText

@Composable
fun FoodDetailsScreen(
    navController: NavController,
    foodItem: FoodItem,
    addToCart: (CartItem) -> Unit
) {
    val context = LocalContext.current
    // Use remember inside a Composable to hold the state
    val quantity = remember { mutableStateOf(1) }

    // Create CartItem
    val cartItem = CartItem(foodItem = foodItem, quantity = quantity)


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ) {
        // Back button
        IconButton(onClick = { navController.popBackStack() }) {
            Image(
                painter = painterResource(id = R.drawable.backbutton),
                contentDescription = "Back",
                modifier = Modifier.size(24.dp) // Ukuran ikon
            )
        }

        // Title
        Text(
            text = foodItem.name,
            style = TitlePage,
            fontSize = 32.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Image
        Image(
            painter = painterResource(id = foodItem.img),
            contentDescription = "Pancake Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(10.dp))
                .align(Alignment.CenterHorizontally),
            contentScale = ContentScale.FillWidth
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Restaurant Name
        Text(
            text = foodItem.restaurant,
            style = TitlePage,
            modifier = Modifier
                .clickable {
                    navController.navigate("restaurantdetails/${foodItem.restaurant}")
                }
        )

        Text(
            text = "Restaurant",
            style = SubText,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Description
        Text(
            text = "Description",
            style = TitlePage,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = foodItem.description,
            style = SubText,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Ingredients
        Text(
            text = "Ingredients",
            style = TitlePage,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column {
            foodItem.ingredients.forEach {
                Text(text = "• $it", style = SubText, color = Color.Black, modifier = Modifier.padding(4.dp))
            }
        }

        Spacer(modifier = Modifier.height(55.dp))

        Button(
            onClick = {
                // Create CartItem with MutableState for quantity
                quantity.value += 1
                addToCart(cartItem) // Add CartItem to cart
                Toast.makeText(context, "Your order has been added to the cart", Toast.LENGTH_SHORT).show()
                navController.navigate("cart")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(57.dp)
                .clip(RoundedCornerShape(15.dp)),
            colors = ButtonDefaults.buttonColors(backgroundColor = RedPrimary)
        ) {
            Text(text = "Add To Cart", style = TitlePage, color = Color.White, fontSize = 18.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPancakeDetailsScreen() {
    // Buat FoodItem contoh untuk preview
    val sampleFoodItem = FoodItem(
        name = "Pancake",
        restaurant = "Best Pancake House",
        price = 25,
        img = R.drawable.menu,  // Gantilah dengan ID drawable yang valid
        description = "Delicious pancake with syrup and butter.",
        ingredients = listOf("Flour", "Egg", "Milk", "Syrup", "Butter")
    )

    // State untuk menyimpan item keranjang
    val cartItems = remember { mutableStateListOf<CartItem>() }

    // Pass FoodItem ke FoodDetailsScreen
    FoodDetailsScreen(
        navController = rememberNavController(),
        foodItem = sampleFoodItem,
        addToCart = { cartItem -> cartItems.add(cartItem) } // Menambahkan CartItem ke keranjang
    )
}

