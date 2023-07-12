package com.pixelcreative.saveable.navigation

import com.pixelcreative.saveable.core.Constants.Companion.EXPENSES_SCREEN
import com.pixelcreative.saveable.core.Constants.Companion.UPDATE_EXPENSES_SCREEN

sealed class Screen(val route: String) {
    object ExpensesScreen: Screen(EXPENSES_SCREEN)
    object UpdateExpenseScreen: Screen(UPDATE_EXPENSES_SCREEN)
}