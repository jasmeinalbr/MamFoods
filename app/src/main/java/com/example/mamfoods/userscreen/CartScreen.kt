package com.example.mamfoods.userscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
    val discount = remember { mutableStateOf(0) } // Example: Discount can be dynamic

    // Menghitung subtotal
    fun calculateSubtotal(): Int {
        return cartItems.sumOf { it.foodItem.price * it.quantity.value } // Access quantity.value for mutableStateOf
    }

    // Menghitung total harga
    fun calculateTotal() {
        val subtotal = calculateSubtotal()
        totalPrice.value = subtotal + deliveryCharge.value - discount.value
    }

    // Kalkulasi total saat memulai
    calculateTotal()

    Scaffold(
        bottomBar = {
            ButtonNavComponent(navController, selectedRoute)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(innerPadding) // Gunakan innerPadding di sini
                .padding(horizontal = 20.dp) // Tambahan padding jika perlu
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            // Header
            Text(
                text = "Explore Your Favorite Food",
                style = TitlePage,
                color = RedPrimary,
                modifier = Modifier.padding(vertical = 24.dp)
            )

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = "Your Cart",
                style = TitlePage,
                color = RedPrimary,
                modifier = Modifier
                    .fillMaxWidth()  // Make sure it takes up the full width
                    .align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (cartItems.isEmpty()) {
                Text(
                    text = "Your cart is empty.",
                    style = TitlePage,
                    color = RedPrimary,
                    modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally),
                    textAlign = TextAlign.Center,
                )
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
                    Spacer(modifier = Modifier.height(8.dp))

                    // Baris dengan teks
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Sub-Total", style = DetailText, color = Color.White)
                        Text(
                            "Rp. ${calculateSubtotal()}",
                            style = DetailText,
                            color = Color.White,
                            textAlign = TextAlign.End
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Delivery Charge", style = DetailText, color = Color.White)
                        Text(
                            "Rp. ${deliveryCharge.value}",
                            style = DetailText,
                            color = Color.White,
                            textAlign = TextAlign.End
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Discount", style = DetailText, color = Color.White)
                        Text(
                            "Rp. ${discount.value}",
                            style = DetailText,
                            color = Color.White,
                            textAlign = TextAlign.End
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Total", style = SubText, fontSize = 18.sp, color = Color.White)
                        Text(
                            "Rp. ${totalPrice.value}",
                            style = SubText,
                            fontSize = 18.sp,
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
                        Text(text = "Proceed", style = TitlePage, fontSize = 18.sp)
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




