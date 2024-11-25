package com.example.mamfoods

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainApp()
        }
    }
}

@Composable
fun MainApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash") {
            SplashScreen(navController)
        }
        composable("signup") {
            SignUpScreen(
                onSignUpClick = { navController.navigate("home") },
                onFacebookSignUpClick = { /* Facebook action */ },
                onGoogleSignUpClick = { /* Google action */ },
                onLoginClick = { navController.navigate("login") }
            )
        }
        composable("login") {
            // Provide the missing parameters for LoginScreen
            LoginScreen(
                onLoginClick = { navController.navigate("home") },  // Navigate to home on successful login
                onFacebookLoginClick = { /* Facebook login action */ },
                onGoogleLoginClick = { /* Google login action */ },
                onSignUpClick = { navController.navigate("signup") } // Navigate to signup screen
            )
        }
        composable("home") {
            HomeScreen(navController)
        }
    }
}
