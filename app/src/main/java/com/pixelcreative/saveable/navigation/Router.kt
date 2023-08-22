package com.pixelcreative.saveable.navigation

interface Router {
    fun goBack()
   fun goToSplashScreen()
   fun goToHomeScreen()
   fun goToDetailScreen()
   fun goToExpenseScreen()
   fun goToAddExpenseScreen(spendType:String)
   fun goToChartScreen()
    fun goToMessageScreen(message: String)
}