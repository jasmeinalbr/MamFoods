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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.app.ui.components.AutoSlidingBanner
import com.example.mamfoods.R
import com.example.mamfoods.ui.theme.BodyText
import com.example.mamfoods.ui.theme.Lato
import com.example.mamfoods.ui.theme.LightGrayColor
import com.example.mamfoods.ui.theme.OffRed
import com.example.mamfoods.ui.theme.OffRed2
import com.example.mamfoods.ui.theme.RedPrimary
import com.example.mamfoods.ui.theme.SubText
import com.example.mamfoods.ui.theme.TitlePage

@Composable
fun FoodAppHomeScreen(
    navController: NavController,
    onViewMoreClick: () -> Unit = { }
) {
    val selectedRoute = remember { mutableStateOf("home") }

    val searchText = remember { mutableStateOf("") }

    val cartItems = remember { mutableStateListOf<FoodItem>() }

    val bannerImages = listOf(
        painterResource(id = R.drawable.banner1),
        painterResource(id = R.drawable.banner1),
        painterResource(id = R.drawable.banner1)
    )

    Scaffold(
        bottomBar = {
            ButtonNavComponent(navController = navController, selectedRoute)
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

            // Search Bar
            Card (
                elevation = 2.dp, // Ketinggian bayangan
                shape = RoundedCornerShape(15.dp), // Bentuk sudut
                modifier = Modifier
                    .fillMaxWidth()

            ) {
                OutlinedTextField(
                    value = searchText.value,
                    onValueChange = { searchText.value = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = OffRed, shape = RoundedCornerShape(15.dp)),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = OffRed2,
                        focusedBorderColor = Color.DarkGray,
                        unfocusedBorderColor = LightGrayColor
                    ),
                    placeholder = {
                        Text(
                            "What do you want to order?",
                            color = OffRed2,
                            fontSize = 12.sp,
                            fontFamily = Lato
                        ) },
                    leadingIcon = {
                        Image(
                            painter = painterResource(id = R.drawable.search),
                            contentDescription = "Search Icon",
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    shape = RoundedCornerShape(15.dp),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                    // Di dalam OutlinedTextField KeyboardActions
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            navController.navigate("search/${searchText.value}")
                        }
                    )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Banner
            AutoSlidingBanner(images = bannerImages)

            Spacer(modifier = Modifier.height(24.dp))

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

            // Popular Items - Only show 3 items
            Column {
                foodItems.take(3).forEach { item ->
                    FoodItemCard(
                        item = item,
                        onFoodCardClick = { selectedFoodItem ->
                            navController.navigate("fooddetails/${selectedFoodItem.name}") // Pass item to the next screen
                        }
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true, apiLevel = 34)
@Composable
fun PreviewFoodAppHomeScreen() {
    FoodAppHomeScreen(navController = rememberNavController())
}