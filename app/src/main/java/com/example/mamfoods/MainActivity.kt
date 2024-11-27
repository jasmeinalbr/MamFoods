package com.example.mamfoods

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mamfoods.viewmodel.AuthViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppNavigation()
        }
    }
}

@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "splash"
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
                onSignUpClick = { /* Not used directly */ },
                onFacebookSignUpClick = { /* Implement Facebook login */ },
                onGoogleSignUpClick = { /* Implement Google login */ },
                onLoginClick = { navController.navigate("login") },
                viewModel = AuthViewModel(),
                onSignUpSuccess = { navController.navigate("home") }
            )
        }
        composable("home") {
            HomeScreen(
                navController = navController
            )
        }
    }
}
