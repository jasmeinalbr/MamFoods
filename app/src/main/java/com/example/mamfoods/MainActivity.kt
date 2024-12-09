package com.example.mamfoods

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mamfoods.userscreen.ButtonNavComponent
import com.example.mamfoods.userscreen.CartItem
import com.example.mamfoods.userscreen.CartScreen
import com.example.mamfoods.userscreen.FoodAppHomeScreen
import com.example.mamfoods.userscreen.FoodDetailsScreen
import com.example.mamfoods.userscreen.FoodItem
import com.example.mamfoods.userscreen.HistoryScreen
import com.example.mamfoods.userscreen.LoginScreen
import com.example.mamfoods.userscreen.OnboardingScreen
import com.example.mamfoods.userscreen.OrderScreen
import com.example.mamfoods.userscreen.OrderSuccessScreen
import com.example.mamfoods.userscreen.PopularScreen
import com.example.mamfoods.userscreen.ProfileData
import com.example.mamfoods.userscreen.ProfileScreen
import com.example.mamfoods.userscreen.RestaurantDetailsScreen
import com.example.mamfoods.userscreen.SignUpScreen
import com.example.mamfoods.userscreen.SplashScreen
import com.example.mamfoods.userscreen.getFoodItemByName
import com.example.mamfoods.userscreen.getRestaurantByName
import com.example.mamfoods.viewmodel.AuthViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigation()

        }
    }
}

@Composable

fun AppNavigation() {
    val navController: NavHostController = rememberNavController()
    val selectedRoute = remember { mutableStateOf("home") }  // Create a state for selected route
    val cartItems = remember { mutableStateListOf<CartItem>() }
    val foodItems = remember { mutableListOf<FoodItem>() }

    // Fun to convert foodItems to CartItems (if needed in future)
    fun convertToCartItems(foodItems: List<FoodItem>): MutableList<CartItem> {
        return foodItems.map { foodItem ->
            // Wrap quantity in mutableStateOf
            CartItem(foodItem = foodItem, quantity = mutableStateOf(1))
        }.toMutableList()
    }

    Scaffold(
        bottomBar = {
            // Tampilkan Bottom Navigation hanya di layar yang mendukungnya
            val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
            if (currentRoute in listOf("home", "cart", "search", "profile", "history")) {
                selectedRoute.value = currentRoute ?: "home" // Update selectedRoute when route changes
                ButtonNavComponent(navController = navController, selectedRoute = selectedRoute)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "splash",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("splash") {
                SplashScreen(
                    onNavigateToOnboarding = {
                        navController.navigate("onboarding")
                    }
                )
            }
            composable("onboarding") {
                OnboardingScreen(
                    onNextClick = {
                        navController.navigate("login")
                    })
            }
            composable("login") {
                LoginScreen(
                    viewModel = AuthViewModel(),
                    onLoginSuccess = {
                        navController.navigate("home")
                    },
                    onSignUpClick = {
                        navController.navigate("signup")
                    },
                    onFacebookLoginClick = {},
                    onGoogleLoginClick = {}
                )
            }
            composable("signup") {
                SignUpScreen(
                    viewModel = AuthViewModel(),
                    onFacebookSignUpClick = { /* Implement Facebook login */ },
                    onGoogleSignUpClick = { /* Implement Google login */ },
                    onLoginClick = { navController.navigate("login") },
                    onSignUpSuccess = { navController.navigate("home") }
                )
            }
            composable("home") {
                FoodAppHomeScreen(
                    navController = navController,
                    onViewMoreClick = { navController.navigate("search") }
                )
            }
            composable("fooddetails/{foodName}") { backStackEntry ->
                val foodName = backStackEntry.arguments?.getString("foodName") ?: ""
                val foodItem = getFoodItemByName(name = foodName)
                FoodDetailsScreen(
                    navController = navController,
                    foodItem = foodItem,
                    addToCart = { cartItems.add(it) } // Tambahkan item ke keranjang
                )
            }
            composable("restaurantdetails/{storeName}") { backStackEntry ->
                val storeName = backStackEntry.arguments?.getString("storeName") ?: ""
                val restaurant = getRestaurantByName(name = storeName) // Ambil restaurant berdasarkan nama
                RestaurantDetailsScreen(navController = navController, restaurant = restaurant)
            }
            composable("cart") {
                // Ensure foodItems is available, whether from a ViewModel, state, or passed from another composable
                val foodItems = remember { mutableStateListOf<FoodItem>() } // Example: Replace with your actual list of food items
                val cartItems = convertToCartItems(foodItems) // Pass the list of food items here
                CartScreen(navController = navController, cartItems = cartItems)
            }
            composable("order") {
                val profileData = remember {
                    ProfileData("Default Name", "Default Address", "123456789", "email@mail.com", "password")
                }
                OrderScreen(navController = navController, profileData = profileData)
            }
            composable("ordersuccess") {
                OrderSuccessScreen(navController)
            }
            composable("search/{query}") { backStackEntry ->
                val query = backStackEntry.arguments?.getString("query") ?: ""
                PopularScreen(navController, query = query)
            }
            composable("search/{query}") {
                PopularScreen(navController, query = "")
            }
            composable("profile") {
                val profileData = remember {
                    ProfileData("Default Name", "Default Address", "123456789", "email@mail.com", "password")
                }
                ProfileScreen(navController = navController, profileData = profileData)
            }
            composable("history") {
                HistoryScreen(navController)
            }
        }
    }
}

