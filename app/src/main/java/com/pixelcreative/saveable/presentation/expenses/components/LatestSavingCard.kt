package com.pixelcreative.saveable.presentation.expenses.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
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

@Composable
@ExperimentalMaterialApi
fun LatestSavingCard(
    expense: Expense,
    navigateToDetailScreen: (expenseId: Long) -> Unit
) {
    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxWidth(),
        onClick = {
            navigateToDetailScreen(expense.id)
        }
    ) {
        Column(
            modifier = Modifier
                .background(Color(0xFFadd8c4))
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            expense.expenseDetailList?.expenseDetail?.forEachIndexed { index, expenseDetail ->
                Text(
                    text = Constants.SPENT_TODAY_TITLE,
                    color = Color(0xFFF5F5F5),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Start
                )
                Text(
                    text = expenseDetail.price.toString(),
                    color = Color(0xFFF5F5F5),
                    fontSize = 28.sp,
                    textAlign = TextAlign.Start
                )
                Text(
                    text = expenseDetail.category,
                    color = Color(0xFFF5F5F5),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start
                )
            }
        }
    }
}