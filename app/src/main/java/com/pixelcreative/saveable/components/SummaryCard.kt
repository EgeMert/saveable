package com.pixelcreative.saveable.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pixelcreative.saveable.domain.model.ExpenseDetail
import com.pixelcreative.saveable.ui.theme.Pinball
import com.pixelcreative.saveable.ui.theme.White

@Preview
@Composable
fun SummaryCard_Preview() {
    SummaryCard(
        recentExpense = listOf(
            ExpenseDetail(
                price = 350.0,
                category = "Shopping"
            ),
            ExpenseDetail(
                price = 200.0,
                category = "Food",
                isIncome = false
            )
        )
    )
}

@Preview
@Composable
fun SummaryCard_Preview2() {
    SummaryCard(
        recentExpense = null
    )
}

@Composable
fun SummaryCard(
    modifier: Modifier = Modifier,
    recentExpense: List<ExpenseDetail>?
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {

        Text(
            modifier = Modifier
                .padding(bottom = 12.dp),
            text = "Recent Expenses",
            color = White,
            style = MaterialTheme.typography.h2
        )
        recentExpense?.let { expenseDetailList ->

            ExpenseDetailListView(expenseDetailList = expenseDetailList)

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                text = "See All",
                color = Pinball,
                style = MaterialTheme.typography.subtitle1,
                textAlign = TextAlign.End
            )

        } ?: run {
            Text(
                modifier = Modifier
                    .padding(8.dp),
                text = "Hiç harcamanız yok, hadi başlayalım \uD83D\uDC7B",
                color = White,
                style = MaterialTheme.typography.h3
            )
        }
    }
}
