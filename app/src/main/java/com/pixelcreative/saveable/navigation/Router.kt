package com.pixelcreative.saveable.navigation

interface Router {
    fun goBack()
   fun goToSplashScreen()
   fun goToHomeScreen()
   fun goToExpenseScreen()
    fun goToMessageScreen(message: String)
}