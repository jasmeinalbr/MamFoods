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
import com.example.mamfoods.ui.theme.YeonSung

@Composable
fun HistoryItem(
    foodItem: FoodItem,
    onBuyAgainClick: () -> Unit
) {

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
                painter = painterResource(foodItem.img),
                contentDescription = foodItem.name,
                modifier = Modifier
                    .size(70.dp)
                    .clip(RoundedCornerShape(16.dp))
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = foodItem.name,
                    style = TitlePage,
                    fontSize = 20.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = foodItem.restaurant,
                    style = SubText,
                    color = Color.LightGray
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Rp. ${foodItem.price}",
                    style = PriceText
                )
            }

            // Tombol "Buy Again"
            Button(
                onClick = onBuyAgainClick,
                colors = ButtonDefaults.buttonColors(backgroundColor = RedPrimary),
                modifier = Modifier
                    .height(36.dp)
                    .clip(RoundedCornerShape(15.dp))
            ) {
                Text(
                    text = "Buy Again",
                    color = Color.White,
                    fontSize = 12.sp,
                    fontFamily = YeonSung
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun PreviewHistoryItem() {
//    val dummyFood = FoodItem(
//        name = "Spicy Crab",
//        restaurant = "Seafood Corner",
//        price = 35000,
//        img = R.drawable.menu, // Ganti dengan gambar Anda
//        description = "Fresh crab with spicy seasoning.",
//        ingredients = listOf("Crab", "Chili", "Garlic", "Onion")
//    )
//
//    HistoryItem(
//        foodItem = dummyFood,
//        onBuyAgainClick = { /* Aksi saat tombol Buy Again ditekan */ }
//    )
//}






