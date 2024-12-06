package com.example.mamfoods

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.mamfoods.model.User
import com.example.mamfoods.viewmodel.AuthViewModel
import com.android.volley.Request
import org.json.JSONArray
import org.json.JSONException

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

@Composable
fun HomeScreen(navController: NavHostController) {
    val users = remember { mutableStateListOf<User>() }
    val context = navController.context

    // Fetch users from API using Volley
    LaunchedEffect(Unit) {
        val url = "http://10.0.2.2:8080/api/users"
        val queue = Volley.newRequestQueue(context)

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                try {
                    val jsonArray = JSONArray(response)
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        val user = User(
                            id = jsonObject.getInt("id").toString(),
                            email = jsonObject.getString("email"),
                            displayName = jsonObject.getString("name")
                        )
                        users.add(user)
                    }
                } catch (e: JSONException) {
                    Toast.makeText(context, "Error parsing JSON: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            },
            { error ->
                Toast.makeText(context, "Error fetching users: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        )
        queue.add(stringRequest)
    }

    // Display user data in UI (replace this with Compose UI elements)
    for (user in users) {
        println("User: ${user.displayName}")
    }
}