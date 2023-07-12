package com.pixelcreative.saveable.navigation.bottomnavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.pixelcreative.saveable.navigation.Screens

object NavBarItems {
    val BarItems = listOf(
        BottomBarItem(
            title = "Home",
            image = Icons.Filled.Home,
            route = Screens.ExpenseScreen.route
        ),
        BottomBarItem(
            title = "Search",
            image = Icons.Filled.Search,
            route = Screens.SplashScreen.route
        ),
        BottomBarItem(
            title = "Notifications",
            image = Icons.Filled.Notifications,
            route = Screens.DetailScreen.route
        ),
        BottomBarItem(
            title = "Messages",
            image = Icons.Filled.Notifications,
            route = Screens.Home.route
        ),
    )
}

data class BottomBarItem(
    val title: String,
    val image: ImageVector,
    val route: String,
    val isActive: Boolean = true
)
