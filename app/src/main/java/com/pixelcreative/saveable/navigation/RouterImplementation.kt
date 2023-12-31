package com.pixelcreative.saveable.navigation

import androidx.navigation.NavHostController

class RouterImplementation(
    private val navHostController: NavHostController,
    private val startDestination:String = Routes.SPLASH
) :Router{
    private fun navigate(
        screen: Screens,
        removeFromHistory: Boolean = false,
        singleTop: Boolean = false
    ) {
        navHostController.apply {
            navigate(screen.route) {
                if (removeFromHistory) {
                    if (singleTop) {
                        popUpTo(screen.route){
                            inclusive = true
                        }
                    } else {
                        popUpTo(0) {
                            saveState = false
                        }
                    }

                } else {
                    restoreState = true
                }
                launchSingleTop = singleTop
            }
        }
    }
    private fun navigateArg(
        route: String,
        removeFromHistory: Boolean = false,
        singleTop: Boolean = false
    ) {
        navHostController.apply {
            navigate(route = route) {
                if (removeFromHistory) {
                    if (singleTop) {
                        popUpTo(Screens.ExpenseScreen.route)
                    } else {
                        popUpTo(0) {
                            saveState = false
                        }
                    }

                } else {
                    restoreState = true
                }
                launchSingleTop = singleTop
            }
        }
    }

    override fun goBack() {
        navHostController.apply {
            popBackStack()
        }
    }

    override fun goToSplashScreen() {
        navigate(Screens.SplashScreen)
    }

    override fun goToHomeScreen() {
        navigate(Screens.ExpenseScreen)
    }

    override fun goToDetailScreen() {
        navigate(Screens.DetailScreen)
    }

    override fun goToExpenseScreen() {
        navigate(Screens.ExpenseScreen)
    }

    override fun goToChartScreen() {
        navigate(Screens.ChartScreen)
    }

    override fun goToProfileScreen() {
        navigate(Screens.ProfileScreen)
    }

    override fun goToAddExpenseScreen(spendType:String) {
        navigateArg(Screens.AddExpenseScreen.passMessage(spendType = spendType))
    }

    override fun goToMessageScreen(message: String) {
        navigateArg(Screens.MessageScreen.passMessage(message = message))
    }

}
