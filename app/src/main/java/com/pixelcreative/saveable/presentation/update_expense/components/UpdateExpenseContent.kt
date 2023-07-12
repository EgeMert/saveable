package com.pixelcreative.saveable.presentation.update_expense.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pixelcreative.saveable.core.Constants.Companion.DAILY_TOTAL_EXPENSE
import com.pixelcreative.saveable.core.Constants.Companion.EXPENSE_DATE
import com.pixelcreative.saveable.core.Constants.Companion.UPDATE_BUTTON
import com.pixelcreative.saveable.domain.model.Expense

@Composable
fun UpdateExpenseContent(
    padding: PaddingValues,
    expense: Expense,
    updateDate: (title: String) -> Unit,
    updateDailyTotalExpense: (author: String) -> Unit,
    updateExpense: (expense: Expense) -> Unit,
    navigateBack: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(padding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = expense.date,
            onValueChange = { date ->
                updateDate(date)
            },
            placeholder = {
                Text(
                    text = EXPENSE_DATE
                )
            }
        )
        Spacer(
            modifier = Modifier.height(8.dp)
        )
        TextField(
            value = expense.dailyTotalExpense,
            onValueChange = { dailyTotalExpense ->
                updateDailyTotalExpense(dailyTotalExpense)
            },
            placeholder = {
                Text(
                    text = DAILY_TOTAL_EXPENSE
                )
            }
        )
        Button(
            onClick = {
                updateExpense(expense)
                navigateBack()
            }
        ) {
            Text(
                text = UPDATE_BUTTON
            )
        }
    }
}