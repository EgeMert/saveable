package com.pixelcreative.saveable.presentation.expenses

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.pixelcreative.saveable.core.Constants
import com.pixelcreative.saveable.domain.model.Expense
import com.pixelcreative.saveable.presentation.expenses.components.AddExpenseAlertDialog
import com.pixelcreative.saveable.presentation.expenses.components.AddExpenseFloatingActionButton
import com.pixelcreative.saveable.presentation.expenses.components.ExpensesContent
import com.pixelcreative.saveable.presentation.expenses.components.ExpensesTopBar

@Composable
@ExperimentalMaterialApi
fun ExpensesScreen(
    viewModel: ExpensesViewModel = hiltViewModel(),
    navigateToUpdateExpenseScreen: (expenseId: Long) -> Unit
) {
    val expenses by viewModel.expenses.collectAsState(
        initial = emptyList()
    )

    val latestExpense by viewModel.latestExpense.collectAsState(
        initial = Expense(
            id = 0L,
            date = Constants.EMPTY_STRING,
            expenseDetailList = null,
            Constants.EMPTY_STRING
        )
    )

    Scaffold(
        topBar = {
            ExpensesTopBar()
        },
        content = { padding ->
            ExpensesContent(
                padding = padding,
                expenses = expenses,
                deleteExpense = { expense ->
                    viewModel.deleteExpense(expense)
                },
                navigateToUpdateExpenseScreen = navigateToUpdateExpenseScreen,
                latestExpense = latestExpense
            )
            AddExpenseAlertDialog(
                openDialog = viewModel.openDialog,
                closeDialog = {
                    viewModel.closeDialog()
                },
                addExpense = { expense ->
                    viewModel.addExpense(expense)
                }
            )
        },
        floatingActionButton = {
            AddExpenseFloatingActionButton(
                openDialog = {
                    viewModel.openDialog()
                }
            )
        }
    )
}