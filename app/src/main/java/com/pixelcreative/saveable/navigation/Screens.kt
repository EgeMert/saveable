package com.pixelcreative.saveable.navigation

sealed class Screens(
    val route:String
) {
    object Home : Screens(route = Path.HOME.path)
    object Splash : Screens(route = Path.SPLASH.path)
    object ExpenseScreen : Screens(route = Path.EXPENSE_SCREEN.path)
    object MessageScreen : Screens(route = Path.MESSAGE_SCREEN.path){
        fun passMessage(message:String):String{
            return "MESSAGE_SCREEN/$message"
        }
    }
}
object Routes {
    const val HOME = "HOME"
    const val MESSAGE_SCREEN = "MESSAGE_SCREEN/{message}"
    const val SPLASH = "SPLASH"
    const val EXPENSE_SCREEN = "EXPENSE_SCREEN"
}
enum class Path(
    val path: String,
    val hasDeeplink:Boolean = false
) {
    HOME(path = Routes.HOME),
    EXPENSE_SCREEN(path = Routes.EXPENSE_SCREEN),
    MESSAGE_SCREEN(path = Routes.MESSAGE_SCREEN),
    SPLASH(path = Routes.SPLASH),
}