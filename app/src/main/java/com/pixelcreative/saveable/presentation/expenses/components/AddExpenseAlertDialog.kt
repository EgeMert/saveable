package com.pixelcreative.saveable.presentation.expenses.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp
import com.pixelcreative.saveable.core.Constants.Companion.ADD_BUTTON
import com.pixelcreative.saveable.core.Constants.Companion.ADD_EXPENSE
import com.pixelcreative.saveable.core.Constants.Companion.DAILY_TOTAL_EXPENSE
import com.pixelcreative.saveable.core.Constants.Companion.DISMISS_BUTTON
import com.pixelcreative.saveable.core.Constants.Companion.EMPTY_STRING
import com.pixelcreative.saveable.core.Constants.Companion.EXPENSE_DATE
import com.pixelcreative.saveable.domain.model.Expense
import kotlinx.coroutines.job

@Composable
fun AddExpenseAlertDialog(
    openDialog: Boolean,
    closeDialog: () -> Unit,
    addExpense: (expense: Expense) -> Unit
) {
    if (openDialog) {
        var date by remember { mutableStateOf(EMPTY_STRING) }
        var dailyTotalExpense by remember { mutableStateOf(EMPTY_STRING) }
        val focusRequester = FocusRequester()

        AlertDialog(
            onDismissRequest = closeDialog,
            title = {
                Text(
                    text = ADD_EXPENSE
                )
            },
            text = {
                Column {
                    TextField(
                        value = date,
                        onValueChange = { date = it },
                        placeholder = {
                            Text(
                                text = EXPENSE_DATE
                            )
                        },
                        modifier = Modifier.focusRequester(focusRequester)
                    )
                    LaunchedEffect(Unit) {
                        coroutineContext.job.invokeOnCompletion {
                            focusRequester.requestFocus()
                        }
                    }
                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )
                    TextField(
                        value = dailyTotalExpense,
                        onValueChange = { dailyTotalExpense = it },
                        placeholder = {
                            Text(
                                text = DAILY_TOTAL_EXPENSE
                            )
                        }
                    )
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        closeDialog()
                        val expense = Expense(
                            id = 0,
                            date = date,
                            expenseDetailList = null,
                            dailyTotalExpense
                        )
                        addExpense(expense)
                    }
                ) {
                    Text(
                        text = ADD_BUTTON
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = closeDialog
                ) {
                    Text(
                        text = DISMISS_BUTTON
                    )
                }
            }
        )
    }
}