package com.pixelcreative.saveable.screens.chart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pixelcreative.saveable.R
import com.pixelcreative.saveable.components.MpBarChart
import com.pixelcreative.saveable.components.MpPieChart
import com.pixelcreative.saveable.core.calculateDailyTotalExpenses
import com.pixelcreative.saveable.core.calculateMonthlyTotalExpenses
import com.pixelcreative.saveable.core.convertExpenseDetailsToMap
import com.pixelcreative.saveable.core.getExpenseSummaryByCategory
import com.pixelcreative.saveable.core.getLocalDateAsString
import com.pixelcreative.saveable.core.getMonthAndYearAsString
import com.pixelcreative.saveable.core.getYearAsString
import com.pixelcreative.saveable.navigation.Router
import com.pixelcreative.saveable.screens.expenses.ExpensesScreenViewModel
import com.pixelcreative.saveable.ui.theme.BlackHtun
import com.pixelcreative.saveable.ui.theme.BluishPurple
import com.pixelcreative.saveable.ui.theme.White

@Composable
@ExperimentalMaterialApi
fun ChartScreen(
    router: Router
) {

    var selectedTabIndex by remember { mutableIntStateOf(0) }

    val tabNames = listOf(
        stringResource(id = R.string.daily),
        stringResource(id = R.string.monthly),
        stringResource(id = R.string.yearly)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BlackHtun)
            .padding(bottom = 30.dp)
    ) {

        val selectedDay by rememberSaveable { mutableStateOf(getLocalDateAsString()) }
        val selectedMonth by rememberSaveable { mutableStateOf(getMonthAndYearAsString()) }
        val selectedYear by rememberSaveable { mutableStateOf(getYearAsString()) }

        val expensesScreenViewModel: ExpensesScreenViewModel = hiltViewModel()

        expensesScreenViewModel.getDailyExpense(selectedDay)
        expensesScreenViewModel.getMonthlyExpense(selectedMonth)
        expensesScreenViewModel.getYearlyExpense(selectedYear)

        val dailyExpense = expensesScreenViewModel.dailyExpense.collectAsState().value
        val monthlyExpense = expensesScreenViewModel.monthlyExpense.collectAsState().value
        val yearlyExpense = expensesScreenViewModel.yearlyExpense.collectAsState().value

        TabRow(
            selectedTabIndex = selectedTabIndex,
            backgroundColor = BlackHtun,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                    color = BluishPurple
                )
            }
        ) {
            for (index in 0..2) {
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = {
                        Text(
                            text = tabNames[index],
                            color = White
                        )
                    }
                )
            }
        }
        when (selectedTabIndex) {
            0 ->
                dailyExpense?.expenseDetailList?.expenseDetail?.let { dailyData ->
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        MpPieChart(mappedExpense = convertExpenseDetailsToMap(dailyData))
                    }
                }

            1 ->
                monthlyExpense?.let { monthlyData ->
                    val monthlyExpenseSummary = getExpenseSummaryByCategory(monthlyData)
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        MpBarChart(expenseDetails = calculateDailyTotalExpenses(monthlyData))
                        MpPieChart(
                            mappedExpense = convertExpenseDetailsToMap(
                                getExpenseSummaryByCategory(
                                    monthlyData
                                )
                            )
                        )
                    }
                }

            2 ->

                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    yearlyExpense?.let { yearlyData ->
                        MpBarChart(expenseDetails = calculateMonthlyTotalExpenses(yearlyData))
                        MpPieChart(
                            mappedExpense = convertExpenseDetailsToMap(
                                getExpenseSummaryByCategory(
                                    yearlyData
                                )
                            )
                        )
                    }
                }
        }
    }
}
