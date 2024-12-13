package com.example.mamfoods.userscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mamfoods.R
import com.example.mamfoods.ui.theme.BodyText
import com.example.mamfoods.ui.theme.DetailText
import com.example.mamfoods.ui.theme.PriceText
import com.example.mamfoods.ui.theme.White
import coil.compose.rememberAsyncImagePainter

@Composable
fun FoodItemCard(
    item: FoodItem,
    onFoodCardClick: (FoodItem) -> Unit = { }
) {
    BoxWithConstraints (
        modifier = Modifier.fillMaxWidth().background(Color.White)
    ) {
        val screenWidth = maxWidth
        val screenHeight = maxHeight

        Card(
            elevation = 2.dp, // Ketinggian bayangan
            shape = RoundedCornerShape(24.dp), // Bentuk sudut
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable { onFoodCardClick(item) } // Pass item to onClick callback
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(White) // Background warna putih
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                // Image
                Box(
                    modifier = Modifier
                        .size(70.dp)
                        .clip(RoundedCornerShape(16.dp))
                ) {
//                    val imagePainter = painterResource(id = item.img)
//                    androidx.compose.foundation.Image(
//                        painter = imagePainter,
//                        contentDescription = item.name,
//                        modifier = Modifier.fillMaxSize()
//                    )
                    Image(
                        painter = rememberAsyncImagePainter(model = item.img),
                        contentDescription = item.name,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                // Texts
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = item.name,
                        style = BodyText,
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.height(12.dp))
//                    Text(
//                        text = item.restaurant,
//                        style = DetailText,
//                        color = Color.Gray,
//                        fontSize = 12.sp
//                    )
                }

                // Price
                Text(
                    text = "Rp. ${item.price}",
                    style = PriceText,
                )
            }
        }
    }

}

//@Composable
//@Preview(showBackground = true)
//fun PreviewFoodItemCard() {
//    FoodItemCard(
//        item = FoodItem(
//            name = "Margherita Pizza",
//            restaurant = "Pizza Palace",
//            price = 45000,
//            img = R.drawable.menu,
//            description = "Classic Italian pizza with fresh tomatoes, mozzarella, and basil.",
//            ingredients = listOf("Pizza Dough", "Tomato Sauce", "Mozzarella", "Basil")
//        ),
//        onFoodCardClick = { }
//    )
//}
