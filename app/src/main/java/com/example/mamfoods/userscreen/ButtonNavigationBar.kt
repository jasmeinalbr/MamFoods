package com.example.mamfoods.userscreen

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.ui.res.painterResource
import com.example.mamfoods.BottomNavItem


@Composable
fun BottomNavigationBar(navController: NavController) {

    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Cart,
        BottomNavItem.Profile,
        BottomNavItem.History
    )
    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color.Black
    ) {
        val currentRoute = navController.currentBackStackEntryAsState()?.value?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon), // Memuat ikon dari drawable
                        contentDescription = item.label
                    )
                },
                label = { Text(item.label) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}
