package com.pixelcreative.saveable.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.pixelcreative.saveable.presentation.expenses.ExpensesScreen
import com.pixelcreative.saveable.presentation.splashscreen.SplashScreen

@Composable
fun SaveableNavGraph(
    navController: NavHostController,
    router: Router,
    startDestination: String = Screens.Splash.route,
    navGraphBuilder: NavGraphBuilder.() -> Unit = {},
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
//        enterTransition = { slideIntoContainer(towards = AnimatedContentTransitionScope.SlideDirection.Start) },
//        popExitTransition = { slideOutOfContainer(towards = AnimatedContentTransitionScope.SlideDirection.Start) }
    ) {
        navGraphBuilder.invoke(this)
        mainNavigation(router)
    }
}
@OptIn(ExperimentalMaterialApi::class)
private fun NavGraphBuilder.mainNavigation(
    router: Router
){
    composable(
        route = Screens.Splash.route
    ){
       SplashScreen(router = router)
    }
    composable(
        route = Screens.Home.route,

        ) {
       // HomeScreen(router = router)
    }
    composable(
        route = Screens.MessageScreen.route,
        arguments = listOf(navArgument("message") {
            type = NavType.StringType
            defaultValue = ""
            nullable  = true
        })
    ) { navBackStackEntry ->
        val message = navBackStackEntry.arguments?.getString("message") ?: ""
       // MessageScreen(router = router, message = message)
    }
    composable(
        route = Screens.ExpenseScreen.route,
    ){
        ExpensesScreen(router = router )
    }
}

