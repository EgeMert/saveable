package com.pixelcreative.saveable.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.pixelcreative.saveable.components.SpendType
import com.pixelcreative.saveable.core.Constants.Companion.EMPTY_STRING
import com.pixelcreative.saveable.screens.addexpense.AddExpenseScreen
import com.pixelcreative.saveable.screens.chart.ChartScreen
import com.pixelcreative.saveable.screens.detail.DetailScreen
import com.pixelcreative.saveable.screens.expenses.ExpensesScreen
import com.pixelcreative.saveable.screens.profile.ProfileScreen
import com.pixelcreative.saveable.screens.splashscreen.SplashScreen

@Composable
fun SaveableNavGraph(
    navController: NavHostController,
    router: Router,
    startDestination: String = Screens.SplashScreen.route,
    navGraphBuilder: NavGraphBuilder.() -> Unit = {},
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        navGraphBuilder.invoke(this)
        mainNavigation(router)
    }
}

@OptIn(ExperimentalMaterialApi::class)
private fun NavGraphBuilder.mainNavigation(
    router: Router,
) {
    composable(
        route = Screens.SplashScreen.route
    ) {
        SplashScreen(router = router)
    }

    composable(
        route = Screens.MessageScreen.route,
        arguments = listOf(navArgument("message") {
            type = NavType.StringType
            defaultValue = ""
            nullable = true
        })
    ) {
    }

    composable(
        route = Screens.ExpenseScreen.route,
    ) {
        ExpensesScreen(router = router)
    }

    composable(
        route = Screens.DetailScreen.route,
    ) {
        DetailScreen(router = router)
    }

    composable(
        route = Screens.ChartScreen.route,
    ) {
        ChartScreen(router = router)
    }
    composable(
        route = Screens.ProfileScreen.route,
    ) {
        ProfileScreen(router = router)
    }

    composable(
        route = Screens.AddExpenseScreen.route,
        arguments = listOf(navArgument("spendType") {
            type = NavType.StringType
            defaultValue = null
            nullable = true
        })
    ) { navBackStackEntry ->
        val type = navBackStackEntry.arguments?.getString("spendType")
        AddExpenseScreen(router = router, spendType =  type)
    }
}
