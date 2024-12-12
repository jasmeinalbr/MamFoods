package com.example.mamfoods.userscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mamfoods.R
import com.example.mamfoods.ui.theme.DarkRed
import com.example.mamfoods.ui.theme.DetailText
import com.example.mamfoods.ui.theme.RedPrimary
import com.example.mamfoods.ui.theme.SubText
import com.example.mamfoods.ui.theme.TitlePage

@Composable
fun CartScreen(navController: NavController, cartItems: MutableList<CartItem>) {
    val selectedRoute = remember { mutableStateOf("cart") }

    // State untuk mengelola total harga
    val totalPrice = remember { mutableStateOf(0) }
    val deliveryCharge = remember { mutableStateOf(10000) } // Example: Fixed delivery charge

    // Menghitung subtotal
    fun calculateSubtotal(): Int {
        return cartItems.sumOf { it.foodItem.price * it.quantity.value } // Access quantity.value for mutableStateOf
    }

    // Menghitung total harga
    fun calculateTotal() {
        val subtotal = calculateSubtotal()
        totalPrice.value = subtotal + deliveryCharge.value
    }

    // Kalkulasi total saat memulai
    calculateTotal()

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
                    .padding(screenWidth * 0.05f),
            ) {
                Spacer(modifier = Modifier.height(screenHeight * 0.03f))
                // Header
                Text(
                    text = "Explore Your Favorite Food",
                    style = TitlePage,
                    color = RedPrimary,
                    fontSize = (screenWidth * 0.06f).value.sp
                )
                Spacer(modifier = Modifier.height(screenHeight * 0.03f))

                Text(
                    text = "Your Cart",
                    style = TitlePage,
                    color = RedPrimary,
                    fontSize = (screenWidth * 0.07f).value.sp,
                    modifier = Modifier
                        .fillMaxWidth()  // Make sure it takes up the full width
                        .align(Alignment.CenterHorizontally),
                    textAlign = TextAlign.Center,
                )

                Spacer(modifier = Modifier.height(screenHeight * 0.02f))

                if (cartItems.isEmpty()) {
                    if (cartItems.isEmpty()) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize(), // Mengisi seluruh area
                            contentAlignment = Alignment.Center // Pusatkan konten di tengah
                        ) {
                            Text(
                                text = "Your cart is empty.",
                                style = TitlePage,
                                color = RedPrimary,
                                fontSize = (screenWidth * 0.06f).value.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                } else {
                    // Daftar item di keranjang
                    LazyColumn(
                        modifier = Modifier.weight(1f) // Agar daftar menyesuaikan ruang
                    ) {
                        items(cartItems) { cartItem -> // Menggunakan cartItem sebagai nama parameter
                            CartItemRow(
                                cartItem = cartItem,
                                onRemoveClick = {
                                    cartItems.remove(cartItem)
                                    calculateTotal()
                                },
                                onQuantityChange = { newQuantity ->
                                    cartItem.quantity.value = newQuantity
                                    calculateTotal()
                                }
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(screenHeight * 0.01f))

                // Bagian Proceed
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(210.dp)
                        .background(
                            color = DarkRed, // Warna latar belakang
                            shape = RoundedCornerShape(24.dp)
                        )
                ) {
                    // Gambar latar belakang
                    Image(
                        painter = painterResource(id = R.drawable.pattern), // Ganti dengan resource gambar lokal
                        contentDescription = "Background Image",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop // Mengatur gambar untuk menutupi seluruh area
                    )

                    // Kolom dengan konten di atas gambar
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                    ) {
                        Spacer(modifier = Modifier.height(screenHeight * 0.01f))

                        // Baris dengan teks
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                "Sub-Total",
                                style = DetailText,
                                color = Color.White,
                                fontSize = (screenWidth * 0.04f).value.sp
                            )
                            Text(
                                "Rp. ${calculateSubtotal()}",
                                style = DetailText,
                                color = Color.White,
                                textAlign = TextAlign.End,
                                fontSize = (screenWidth * 0.04f).value.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(screenHeight * 0.01f))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                "Delivery Charge",
                                style = DetailText,
                                color = Color.White,
                                fontSize = (screenWidth * 0.04f).value.sp
                            )
                            Text(
                                "Rp. ${deliveryCharge.value}",
                                style = DetailText,
                                color = Color.White,
                                textAlign = TextAlign.End,
                                fontSize = (screenWidth * 0.04f).value.sp
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                "Total",
                                style = SubText,
                                fontSize = (screenWidth * 0.045f).value.sp,
                                color = Color.White)
                            Text(
                                "Rp. ${totalPrice.value}",
                                style = SubText,
                                fontSize = (screenWidth * 0.045f).value.sp,
                                color = Color.White,
                                textAlign = TextAlign.End
                            )
                        }

                        // Spacer untuk mendorong tombol ke bawah
                        Spacer(modifier = Modifier.weight(1f))

                        Button(
                            onClick = { navController.navigate("order") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(57.dp)
                                .clip(RoundedCornerShape(15.dp)),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
                        ) {
                            Text(
                                text = "Proceed",
                                style = TitlePage,
                                fontSize = (screenWidth * 0.05f).value.sp,
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun PreviewCartScreen() {
    val navController = rememberNavController()

    // Use remember to avoid recreating the state on recomposition
    val sampleCartItems = remember {
        mutableListOf(
            CartItem(
                foodItem = FoodItem(
                    name = "Pancake",
                    restaurant = "Cake Castle",
                    price = 20000,
                    img = R.drawable.menu,
                    description = "Soft and fluffy pancakes topped with fresh fruit and whipped cream.",
                    ingredients = listOf("Flour", "Eggs", "Milk", "Strawberries", "Whipped Cream")
                ),
                quantity = mutableStateOf(2) // Ensure it's a MutableState
            ),
            CartItem(
                foodItem = FoodItem(
                    name = "Burger",
                    restaurant = "Burger Town",
                    price = 50000,
                    img = R.drawable.menu,
                    description = "Juicy beef burger with fresh vegetables.",
                    ingredients = listOf("Beef", "Lettuce", "Tomato", "Cheese", "Bun")
                ),
                quantity = mutableStateOf(1) // Ensure it's a MutableState
            )
        )
    }

    CartScreen(navController = navController, cartItems = sampleCartItems)
}




