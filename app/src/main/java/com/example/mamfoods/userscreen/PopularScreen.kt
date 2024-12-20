package com.example.mamfoods.userscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mamfoods.R
import com.example.mamfoods.ui.theme.Lato
import com.example.mamfoods.ui.theme.LightGrayColor
import com.example.mamfoods.ui.theme.OffRed
import com.example.mamfoods.ui.theme.OffRed2
import com.example.mamfoods.ui.theme.RedPrimary
import com.example.mamfoods.ui.theme.TitlePage

@Composable
fun PopularScreen(navController: NavController,query: String) {
    val selectedRoute = remember { mutableStateOf("search/{query}") }
    val searchText = remember { mutableStateOf(query) } // Manage search query as state

    val filteredItems = if (query.isEmpty()) {
        foodItems
    } else {
        foodItems.filter { it.name.contains(query, ignoreCase = true) }
    }

    val cartItems = remember { mutableStateListOf<FoodItem>() }

    Scaffold (
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
                    .padding(horizontal = 20.dp)
            ) {
                Spacer(modifier = Modifier.height(screenHeight * 0.05f))

                // Header
                Text(
                    text = "Explore Your Favorite Food",
                    style = TitlePage,
                    color = RedPrimary,
                    fontSize = (screenWidth * 0.06f).value.sp,
                )
                Spacer(modifier = Modifier.height(screenHeight * 0.02f))

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
                        textStyle = TextStyle(
                            fontFamily = Lato,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = (screenWidth * 0.035f).value.sp,
                        ),
                        placeholder = {
                            Text(
                                "What do you want to order?",
                                color = OffRed2,
                                fontSize = (screenWidth * 0.035f).value.sp,
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

                Spacer(modifier = Modifier.height(screenHeight * 0.02f))

                Text(
                    text = "Popular",
                    style = TitlePage,
                    color = RedPrimary,
                    fontSize = (screenWidth * 0.055f).value.sp,
                    modifier = Modifier
                        .fillMaxWidth()  // Make sure it takes up the full width
                        .align(Alignment.CenterHorizontally),
                    textAlign = TextAlign.Center,
                )

                Spacer(modifier = Modifier.height(screenHeight * 0.02f))

                // LazyColumn for filtered items
                LazyColumn {
                    items(filteredItems) { item -> // Corrected this part
                        FoodItemCard(
                            item = item,
                            onFoodCardClick = { selectedFoodItem ->
                                navController.navigate("foodDetailScreen/${selectedFoodItem.name}") // Pass item to the next screen
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, apiLevel = 34)
@Composable
fun PreviewPopularScreen() {
    val navController = rememberNavController()
    PopularScreen(navController = navController, query = "")
}