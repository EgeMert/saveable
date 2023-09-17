package com.pixelcreative.saveable.components.analysis

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pixelcreative.saveable.R
import com.pixelcreative.saveable.components.ExpenseDetailListView
import com.pixelcreative.saveable.core.formatDoubleToString
import com.pixelcreative.saveable.core.getExpenseSummaryByCategory
import com.pixelcreative.saveable.core.getMonthAndYearAsString
import com.pixelcreative.saveable.core.getTotalPrice
import com.pixelcreative.saveable.screens.expenses.ExpensesScreenViewModel
import com.pixelcreative.saveable.ui.theme.BluishPurple
import com.pixelcreative.saveable.ui.theme.Emerald
import com.pixelcreative.saveable.ui.theme.Pinball

@Composable
fun MonthlyExpenseAnalysis() {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {

        var selectedMonth by rememberSaveable { mutableStateOf(getMonthAndYearAsString()) }

        val expensesScreenViewModel: ExpensesScreenViewModel = hiltViewModel()

        expensesScreenViewModel.getMonthlyExpense(selectedMonth)

        val monthlyExpense = expensesScreenViewModel.monthlyExpense.collectAsState().value

        Text(
            modifier = Modifier.padding(top = 20.dp),
            text = stringResource(id = R.string.total_balance_title),
            color = Pinball,
            style = MaterialTheme.typography.h5
        )

        Text(
            modifier = Modifier.padding(top = 12.dp),
            text =
            stringResource(
                id = R.string.total_balance_info,
                (getTotalPrice(monthlyExpense)).formatDoubleToString()
            ),
            color = if (getTotalPrice(monthlyExpense) > 0) {
                Emerald
            } else {
                Pinball
            },
            style = MaterialTheme.typography.h3
        )

        Text(
            modifier = Modifier
                .padding(top = 20.dp),
            text = stringResource(
                id = R.string.monthly_categories_count_info,
                getExpenseSummaryByCategory(monthlyExpense).size.or(0)
            ),
            color = Pinball,
            style = MaterialTheme.typography.h6,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2
        )

        Divider(
            modifier = Modifier
                .padding(top = 4.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(color = BluishPurple)
        )

        ExpenseDetailListView(
            modifier = Modifier
                .navigationBarsPadding()
                .padding(top = 12.dp),
            expenseDetailList = getExpenseSummaryByCategory(monthlyExpense).reversed(),
            emptyListModifier = Modifier
                .padding(vertical = 8.dp)
        )
    }
}
