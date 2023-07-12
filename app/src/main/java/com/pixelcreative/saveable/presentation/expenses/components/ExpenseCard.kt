package com.pixelcreative.saveable.presentation.expenses.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pixelcreative.saveable.domain.model.Expense

@Composable
@ExperimentalMaterialApi
fun ExpenseCard(
    expense: Expense,
    deleteExpense: () -> Unit,
    navigateToUpdateExpenseScreen: (expenseId: Long) -> Unit
) {
    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        onClick = {
            navigateToUpdateExpenseScreen(expense.id)
        }
    ) {
        Row(
            modifier = Modifier
                .background(Color(0xFFadd8c4))
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            expense.expenseDetailList?.expenseDetail?.forEachIndexed { index, expenseDetail ->
                Column {
                    TextTitle(
                        expenseTitle = expenseDetail.price.toString()
                    )
                    TextAuthor(
                        expenseAuthor = expenseDetail.category
                    )
                }
            }

            Spacer(
                modifier = Modifier.weight(1f)
            )
            DeleteIcon(
                deleteExpense = deleteExpense
            )
        }
    }
}