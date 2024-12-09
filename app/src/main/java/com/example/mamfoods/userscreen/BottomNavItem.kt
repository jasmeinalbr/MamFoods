package com.example.mamfoods.userscreen

import com.example.mamfoods.R

sealed class BottomNavItem(val route: String, val label: String, val icon: Int) {
    object Home : BottomNavItem("home", "Home", R.drawable.iconhome)
    object Cart : BottomNavItem("cart", "Cart", R.drawable.iconcart)
    object Profile : BottomNavItem("profile", "Profile", R.drawable.iconprofile)
    object History : BottomNavItem("history", "History", R.drawable.iconhistory)
    object Search : BottomNavItem("search/{query}", "Search", R.drawable.searchrefraction) {
        fun withQuery(query: String) = "search/$query"
    }
}
