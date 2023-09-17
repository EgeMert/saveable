package com.pixelcreative.saveable.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pixelcreative.saveable.R
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
        ),
        onSeeAllClicked = { }
    )
}

@Preview
@Composable
fun SummaryCard_Preview2() {
    SummaryCard(
        recentExpense = null,
        onSeeAllClicked = { }
    )
}

@Composable
fun SummaryCard(
    modifier: Modifier = Modifier,
    recentExpense: List<ExpenseDetail>?,
    onSeeAllClicked:() -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {

        Text(
            modifier = Modifier
                .padding(bottom = 12.dp),
            text = stringResource(id = R.string.recent_expenses_text),
            color = White,
            style = MaterialTheme.typography.h2
        )
        recentExpense?.let { expenseDetailList ->

            ExpenseDetailListView(
                expenseDetailList = expenseDetailList,
                emptyListModifier = Modifier
                    .padding(8.dp)
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .clickable {
                        onSeeAllClicked.invoke()
                    },
                text = stringResource(id = R.string.see_all_text),
                color = Pinball,
                style = MaterialTheme.typography.subtitle1,
                textAlign = TextAlign.End
            )

        } ?: run {
            EmptyDataComponent(modifier = Modifier.padding(vertical = 8.dp))
        }
    }
}
