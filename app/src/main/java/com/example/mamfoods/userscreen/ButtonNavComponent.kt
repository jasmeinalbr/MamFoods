package com.example.mamfoods.userscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mamfoods.BottomNavItem
import com.example.mamfoods.ui.theme.BodyText
import com.example.mamfoods.ui.theme.Lato

@Composable
fun CustomBottomNavigation(
    navController: NavController,
    selectedRoute: MutableState<String>
) {
    val bottomNavItems = listOf(
        BottomNavItem.Home,
        BottomNavItem.Cart,
        BottomNavItem.History,
        BottomNavItem.Profile
    )

    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color.Black,
        elevation = 8.dp,
        modifier = Modifier
            .height(76.dp)
            .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
            .background(Color.White)
    ) {
        bottomNavItems.forEach { item ->
            val isSelected = selectedRoute.value == item.route

            BottomNavigationItem(
                selected = isSelected,
                onClick = {
                    selectedRoute.value = item.route
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = @androidx.compose.runtime.Composable {
                    Row(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .weight(1f)
                            .wrapContentSize() // Menyesuaikan ukuran konten
                            .background(
                                color = if (isSelected) Color(0xFFE7F7F0) else Color.Transparent,
                                shape = RoundedCornerShape(12.dp) // Sesuai dengan desain
                            )
                            .padding(horizontal = 18.dp, vertical = 12.dp), // Tambahkan padding agar lebih rapi
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = item.label,
                            modifier = Modifier.size(24.dp),
                            tint = Color.Black,
                        )
                        if (isSelected) {
                            Spacer(modifier = Modifier.width(4.dp)) // Jarak antara ikon dan teks
                            Text(
                                text = item.label,
                                fontFamily = Lato,
                                fontSize = 12.sp,
                                color = Color.Black
                            )
                        }
                    }
                }, alwaysShowLabel = false
            )
        }
    }
}
