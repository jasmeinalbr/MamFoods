package com.example.mamfoods

sealed class BottomNavItem(val route: String, val label: String, val icon: Int) {
    object Home : BottomNavItem("home", "Home", R.drawable.iconhome)
    object Cart : BottomNavItem("cart", "Cart", R.drawable.iconcart)
    object Profile : BottomNavItem("profile", "Profile", R.drawable.iconprofile)
    object History : BottomNavItem("history", "History", R.drawable.iconhistory)
}
