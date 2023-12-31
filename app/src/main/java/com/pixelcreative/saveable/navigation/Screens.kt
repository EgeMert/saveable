package com.pixelcreative.saveable.navigation

sealed class Screens(
    val route: String
) {
    object Home : Screens(route = Path.HOME.path)
    object SplashScreen : Screens(route = Path.SPLASH.path)
    object DetailScreen : Screens(route = Path.DETAIL_SCREEN.path)
    object ProfileScreen : Screens(route = Path.PROFILE_SCREEN.path)
    object ExpenseScreen : Screens(route = Path.EXPENSE_SCREEN.path)
    object AddExpenseScreen : Screens(route = Path.ADD_EXPENSE_SCREEN.path) {
        fun passMessage(spendType: String): String {
            return "ADD_EXPENSE_SCREEN/$spendType"
        }
    }

    object ChartScreen : Screens(route = Path.CHART_SCREEN.path)
    object MessageScreen : Screens(route = Path.MESSAGE_SCREEN.path) {
        fun passMessage(message: String): String {
            return "MESSAGE_SCREEN/$message"
        }
    }
}

object Routes {
    const val HOME = "HOME"
    const val MESSAGE_SCREEN = "MESSAGE_SCREEN/{message}"
    const val SPLASH = "SPLASH"
    const val DETAIL_SCREEN = "DETAIL_SCREEN"
    const val EXPENSE_SCREEN = "EXPENSE_SCREEN"
    const val ADD_EXPENSE_SCREEN = "ADD_EXPENSE_SCREEN/{spendType}"
    const val CHART_SCREEN = "CHART_SCREEN"
    const val PROFILE_SCREEN = "PROFILE_SCREEN"
}

enum class Path(
    val path: String,
    val hasDeeplink: Boolean = false
) {
    HOME(path = Routes.HOME),
    EXPENSE_SCREEN(path = Routes.EXPENSE_SCREEN),
    MESSAGE_SCREEN(path = Routes.MESSAGE_SCREEN),
    DETAIL_SCREEN(path = Routes.DETAIL_SCREEN),
    ADD_EXPENSE_SCREEN(path = Routes.ADD_EXPENSE_SCREEN),
    CHART_SCREEN(path = Routes.CHART_SCREEN),
    SPLASH(path = Routes.SPLASH),
    PROFILE_SCREEN(path = Routes.PROFILE_SCREEN),
}