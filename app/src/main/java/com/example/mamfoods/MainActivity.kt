package com.example.mamfoods

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mamfoods.userscreen.BottomNavigationBar
import com.example.mamfoods.userscreen.CartScreen
import com.example.mamfoods.userscreen.FoodAppHomeScreen
import com.example.mamfoods.userscreen.HistoryScreen
import com.example.mamfoods.userscreen.HomeScreen
import com.example.mamfoods.userscreen.LoginScreen
import com.example.mamfoods.userscreen.PopularScreen
import com.example.mamfoods.userscreen.ProfileScreen
import com.example.mamfoods.userscreen.SignUpScreen
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

    Scaffold(
        bottomBar = {
            // Tampilkan Bottom Navigation hanya di layar yang mendukungnya
            val currentRoute =
                navController.currentBackStackEntryAsState()?.value?.destination?.route
            if (currentRoute in listOf("home", "cart", "profile", "history")) {
                BottomNavigationBar(navController)
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
                    }
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
                    onViewMoreClick = { navController.navigate("popular") }
                )
            }
            composable("cart") {
                CartScreen()
            }
            composable("profile") {
                ProfileScreen()
            }
            composable("history") {
                HistoryScreen()
            }
            composable("popular") {
                PopularScreen()
            }
        }
    }
}

