package com.pixelcreative.saveable.screens.expenses

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pixelcreative.saveable.components.DailySummary
import com.pixelcreative.saveable.components.SummaryCard
import com.pixelcreative.saveable.domain.model.Expense
import com.pixelcreative.saveable.domain.model.ExpenseDetail
import com.pixelcreative.saveable.domain.model.ExpenseDetailList
import com.pixelcreative.saveable.navigation.Router
import com.pixelcreative.saveable.ui.theme.Black
import com.pixelcreative.saveable.ui.theme.White
import java.time.LocalDate

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
        initial = null
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Black)
    ) {
        Text(
            modifier = Modifier
                .padding(
                    top = 24.dp,
                    start = 16.dp,
                    end = 16.dp
                ),
            text = "Günlük Özet",
            color = White,
            style = MaterialTheme.typography.h2
        )

        DailySummary(
            modifier = Modifier.padding(16.dp),
            dailyExpense = Expense(
                id = 0L,
                date = "Date",
                expenseDetailList = ExpenseDetailList(
                    expenseDetail = listOf(
                        ExpenseDetail(
                            id = LocalDate.now().toString(),
                            price = 350.0,
                            isIncome = false,
                            category = "Alışveriş"
                        ),
                        ExpenseDetail(
                            id = LocalDate.now().toString(),
                            price = 200.0,
                            isIncome = false,
                            category = "Giyim"
                        )
                    )
                ),
                dailyTotalExpense = "550.0 TL"
            )
        )

        Text(
            modifier = Modifier
                .padding(
                    top = 24.dp,
                    start = 16.dp,
                    end = 16.dp
                ),
            text = "Son Harcamalar",
            color = White,
            style = MaterialTheme.typography.h2
        )

        SummaryCard(
            modifier = Modifier.padding(vertical = 24.dp, horizontal = 16.dp),
            latestExpense = Expense(
                id = 0L,
                date = "Date",
                expenseDetailList = ExpenseDetailList(
                    expenseDetail = listOf(
                        ExpenseDetail(
                            id = LocalDate.now().toString(),
                            price = 350.0,
                            isIncome = false,
                            category = "Alışveriş"
                        ),
                        ExpenseDetail(
                            id = LocalDate.now().toString(),
                            price = 200.0,
                            isIncome = false,
                            category = "Giyim"
                        )
                    )
                ),
                dailyTotalExpense = "550.0 TL"
            )
        )
    }
}