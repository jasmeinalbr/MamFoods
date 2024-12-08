package com.example.mamfoods.userscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mamfoods.R
import com.example.mamfoods.ui.theme.PriceText
import com.example.mamfoods.ui.theme.RedPrimary
import com.example.mamfoods.ui.theme.SubText
import com.example.mamfoods.ui.theme.TitlePage
import com.example.mamfoods.ui.theme.White

@Composable
fun CartItemRow(
    cartItem: CartItem, // CartItem sekarang menerima quantity sebagai MutableState
    onRemoveClick: () -> Unit,
    onQuantityChange: (Int) -> Unit // Perbarui quantity di luar CartItemRow
) {
    var quantity by remember { mutableStateOf(cartItem.quantity.value) } // Ambil quantity dari CartItem yang diterima

    Card(
        elevation = 2.dp,
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(White)
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = cartItem.foodItem.img),
                contentDescription = cartItem.foodItem.name,
                modifier = Modifier
                    .size(70.dp)
                    .clip(RoundedCornerShape(16.dp))
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    cartItem.foodItem.name,
                    style = TitlePage,
                    fontSize = 20.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    cartItem.foodItem.restaurant,
                    style = SubText,
                    color = Color.LightGray
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    "Rp. ${cartItem.foodItem.price}",
                    style = PriceText
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                    Button(
                        modifier = Modifier.size(26.dp),
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = RedPrimary),
                        onClick = {
                            if (quantity > 1) {
                                quantity -= 1
                                cartItem.quantity.value = quantity // Update quantity state
                                onQuantityChange(quantity) // Update quantity
                            }
                        },
                    ) { Text(
                        "-",
                        color = Color.White,
                        fontSize = 18.sp,
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Text(
                        text = "$quantity",
                        fontSize = 16.sp,
                        style = SubText,
                        color = Color.Black,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Button(
                        modifier = Modifier.size(26.dp),
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = RedPrimary),
                        onClick = {
                            quantity += 1
                            cartItem.quantity.value = quantity // Update quantity state
                            onQuantityChange(quantity) // Update quantity
                        }
                    ) { Text(
                        "+",
                        color = Color.White,
                        fontSize = 16.sp,
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Remove button with image
                IconButton(
                    onClick = { onRemoveClick() },
                    modifier = Modifier.size(20.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.trash), // Replace with your image
                        contentDescription = "Remove Item",
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewCartItemRow() {
    // Membuat FoodItem sample
    val sampleItem = FoodItem(
        name = "Pizza",
        restaurant = "Restaurant A",
        price = 120,
        img = R.drawable.menu, // Ganti dengan gambar yang sesuai di folder drawable
        description = "Soft and fluffy pancakes topped with fresh fruit and whipped cream.",
        ingredients = listOf("Flour", "Eggs", "Milk", "Strawberries", "Whipped Cream")
    )

    // Membuat CartItem dengan quantity 2 sebagai contoh
    val cartItem = CartItem(foodItem = sampleItem, quantity = mutableStateOf(2)) // Using mutableStateOf for quantity

    // Menampilkan CartItemRow dengan cartItem yang sudah disiapkan
    CartItemRow(
        cartItem = cartItem, // Pass the entire CartItem
        onRemoveClick = {
            // Implementasikan jika diperlukan
            println("Item removed: ${cartItem.foodItem.name}")
        },
        onQuantityChange = { updatedQuantity ->
            // Handle perubahan quantity
            cartItem.quantity.value = updatedQuantity // Update quantity (with .value since it's a mutableState)
            println("Quantity updated: ${cartItem.quantity.value}")
        }
    )
}



