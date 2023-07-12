package com.pixelcreative.saveable.navigation

interface Router {
    fun goBack()
   fun goToSplashScreen()
   fun goToHomeScreen()
   fun goToDetailScreen()
   fun goToExpenseScreen()
    fun goToMessageScreen(message: String)
}