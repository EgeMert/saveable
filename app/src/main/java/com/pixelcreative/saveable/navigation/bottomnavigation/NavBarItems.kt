package com.pixelcreative.saveable.navigation.bottomnavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.ui.graphics.vector.ImageVector
import com.pixelcreative.saveable.navigation.Screens

object NavBarItems {
    val BarItems = listOf(
        BottomBarItem(
            image = Icons.Filled.Home,
            route = Screens.ExpenseScreen.route
        ),
        BottomBarItem(
            image = Icons.Filled.DateRange,
            route = Screens.DetailScreen.route
        ),
        BottomBarItem(
            image = Icons.Filled.Add,
            route = Screens.AddExpenseScreen.route
        ),
        BottomBarItem(
            image = Icons.Filled.List,
            route = Screens.ChartScreen.route
        ),
        BottomBarItem(
            image = Icons.Filled.MoreVert,
            route = Screens.Home.route
        )
    )
}

data class BottomBarItem(
    val image: ImageVector,
    val route: String,
    val isActive: Boolean = true
)
