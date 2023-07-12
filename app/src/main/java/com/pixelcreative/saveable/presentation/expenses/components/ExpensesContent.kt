package com.pixelcreative.saveable.presentation.expenses.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pixelcreative.saveable.core.Constants
import com.pixelcreative.saveable.domain.model.Expense
import com.pixelcreative.saveable.domain.repository.Expenses

@Composable
@ExperimentalMaterialApi
fun ExpensesContent(
    padding: PaddingValues,
    expenses: Expenses,
    deleteExpense: (expense: Expense) -> Unit,
    navigateToUpdateExpenseScreen: (expenseId: Long) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(8.dp)
    ) {

        item {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 16.dp),
                    text = Constants.SPENT_TODAY,
                    color = Color.DarkGray,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Start
                )

            }
        }

        items(
            items = expenses
        ) { expense ->
            ExpenseCard(
                expense = expense,
                deleteExpense = {
                    deleteExpense(expense)
                },
                navigateToUpdateExpenseScreen ={}
            )
        }
    }
}