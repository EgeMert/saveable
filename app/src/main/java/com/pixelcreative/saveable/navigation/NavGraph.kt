package com.pixelcreative.saveable.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType.Companion.IntType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.pixelcreative.saveable.core.Constants.Companion.EXPENSE_ID
import com.pixelcreative.saveable.navigation.Screen.UpdateExpenseScreen
import com.pixelcreative.saveable.presentation.expenses.ExpensesScreen
import com.pixelcreative.saveable.presentation.update_expense.UpdateExpenseScreen

@Composable
@ExperimentalMaterialApi
fun NavGraph (
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.ExpensesScreen.route
    ) {
        composable(
            route = Screen.ExpensesScreen.route
        ) {
            ExpensesScreen(
                navigateToUpdateExpenseScreen = { expenseId ->
                    navController.navigate(
                        route = "${UpdateExpenseScreen.route}/${expenseId}"
                    )
                }
            )
        }
        composable(
            route = "${Screen.UpdateExpenseScreen.route}/{$EXPENSE_ID}",
            arguments = listOf(
                navArgument(EXPENSE_ID) {
                    type = IntType
                }
            )
        ) { backStackEntry ->
            val expenseId = backStackEntry.arguments?.getLong(EXPENSE_ID) ?: 0L
            UpdateExpenseScreen(
                expenseId = expenseId,
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}