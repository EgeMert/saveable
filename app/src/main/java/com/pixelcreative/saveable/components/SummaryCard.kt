package com.pixelcreative.saveable.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pixelcreative.saveable.core.getImageFromLabel
import com.pixelcreative.saveable.domain.model.Expense
import com.pixelcreative.saveable.domain.model.ExpenseDetail
import com.pixelcreative.saveable.domain.model.ExpenseDetailList
import com.pixelcreative.saveable.ui.theme.Pinball
import com.pixelcreative.saveable.ui.theme.White

@Preview
@Composable
fun SummaryCard_Preview() {
    SummaryCard(
        latestExpense = Expense(
            id = 0L,
            date = "Date",
            expenseDetailList = ExpenseDetailList(
                expenseDetail = listOf(
                    ExpenseDetail(
                        price = 350.0,

                        category = "Alışveriş"
                    ),
                    ExpenseDetail(
                        price = 200.0,
                        category = "Giyim"
                    )
                )
            ),
            dailyTotalExpense = 550.00,
            incomeDetailList = null
        )
    )
}

@Preview
@Composable
fun SummaryCard_Preview2() {
    SummaryCard(
        latestExpense = null
    )
}

@Composable
fun SummaryCard(
    modifier: Modifier = Modifier,
    latestExpense: Expense?
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

        latestExpense?.let { latestExpense ->
            latestExpense.expenseDetailList?.expenseDetail?.forEachIndexed { index, expenseDetail ->

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val imagePainter = getImageFromLabel(expenseDetail.category ?: "Food")
                    imagePainter?.let {
                        Image(
                            modifier = Modifier.size(44.dp),
                            painter = it,
                            contentDescription = expenseDetail.category.orEmpty()
                        )
                    }
                    Text(
                        modifier = Modifier
                            .padding(start = 8.dp),
                        text = expenseDetail.category.orEmpty(),
                        color = White,
                        style = MaterialTheme.typography.h5
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = "$ " + expenseDetail.price.toString(),
                        color = White,
                        style = MaterialTheme.typography.h5
                    )
                }
            }

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