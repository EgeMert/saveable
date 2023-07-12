package com.pixelcreative.saveable.screens.expenses

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.pixelcreative.saveable.core.Constants
import com.pixelcreative.saveable.domain.model.Expense
import com.pixelcreative.saveable.navigation.Router

@Composable
@ExperimentalMaterialApi
fun ExpensesScreen(
    router: Router
) {
    val viewModel: ExpensesViewModel = hiltViewModel()

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
}