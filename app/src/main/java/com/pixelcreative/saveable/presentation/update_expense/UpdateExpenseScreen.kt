package com.pixelcreative.saveable.presentation.update_expense

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.pixelcreative.saveable.presentation.expenses.ExpensesViewModel
import com.pixelcreative.saveable.presentation.update_expense.components.UpdateExpenseContent
import com.pixelcreative.saveable.presentation.update_expense.components.UpdateExpenseTopBar

@Composable
fun UpdateExpenseScreen(
    viewModel: ExpensesViewModel = hiltViewModel(),
    expenseId: Long,
    navigateBack: () -> Unit
) {

    LaunchedEffect(Unit) {
        viewModel.getExpense(expenseId)
    }

    Scaffold(
        topBar = {
            UpdateExpenseTopBar(
                navigateBack = navigateBack
            )
        },
        content = { padding ->
            UpdateExpenseContent(
                padding = padding,
                expense = viewModel.expense,
                updateDate = { date ->
                    viewModel.updateTitle(date)
                },
                updateDailyTotalExpense = { dailyTotalExpense ->
                    viewModel.updateAuthor(dailyTotalExpense)
                },
                updateExpense = { expense ->
                    viewModel.updateExpense(expense)
                },
                navigateBack = navigateBack
            )
        }
    )
}