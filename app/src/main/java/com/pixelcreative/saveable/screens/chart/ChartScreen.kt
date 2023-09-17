package com.pixelcreative.saveable.screens.chart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pixelcreative.saveable.R
import com.pixelcreative.saveable.components.EmptyDataComponent
import com.pixelcreative.saveable.components.MpBarChart
import com.pixelcreative.saveable.components.MpPieChart
import com.pixelcreative.saveable.core.TotalExpenseWithValue
import com.pixelcreative.saveable.core.calculateDailyTotalExpenses
import com.pixelcreative.saveable.core.calculateMonthlyTotalExpenses
import com.pixelcreative.saveable.core.convertExpenseDetailsToMap
import com.pixelcreative.saveable.core.getEnumByLocalizedLabel
import com.pixelcreative.saveable.core.getExpenseSummaryByCategory
import com.pixelcreative.saveable.core.getImageFromLabel
import com.pixelcreative.saveable.core.getLocalDateAsString
import com.pixelcreative.saveable.core.getMonthAndYearAsString
import com.pixelcreative.saveable.core.getYearAsString
import com.pixelcreative.saveable.core.roundToDecimal
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
                    val mappedExpense = convertExpenseDetailsToMap(dailyData)
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        MpPieChart(
                            modifier = Modifier
                                .height(LocalConfiguration.current.screenHeightDp.dp / 3)
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp, vertical = 12.dp),
                            mappedExpense = mappedExpense
                        )
                        val totalSum = mappedExpense.values.sum()

                        val totalExpenseList: List<TotalExpenseWithValue> =
                            mappedExpense.entries.map { (key, value) ->
                                TotalExpenseWithValue(key, value)
                            }

                        PieChartList(totalSum, totalExpenseList)

                    }
                } ?: run {
                    EmptyDataComponent(modifier = Modifier.padding(8.dp))
                }

            1 ->
                if (!monthlyExpense.isNullOrEmpty()) {
                    val mappedMonthlyExpense =
                        convertExpenseDetailsToMap(getExpenseSummaryByCategory(monthlyExpense))
                    PieAndBarChart(
                        barChartData = calculateDailyTotalExpenses(monthlyExpense),
                        pieChartData = mappedMonthlyExpense
                    )
                    val totalSum = mappedMonthlyExpense.values.sum()

                    val totalExpenseList: List<TotalExpenseWithValue> =
                        mappedMonthlyExpense.entries.map { (key, value) ->
                            TotalExpenseWithValue(key, value)
                        }
                    PieChartList(totalSum, totalExpenseList)
                } else {
                    EmptyDataComponent(modifier = Modifier.padding(8.dp))
                }

            2 ->
                if (!yearlyExpense.isNullOrEmpty()) {
                    val mappedYearlyExpense =
                        convertExpenseDetailsToMap(getExpenseSummaryByCategory(yearlyExpense))
                    PieAndBarChart(
                        barChartData = calculateMonthlyTotalExpenses(yearlyExpense),
                        pieChartData = mappedYearlyExpense
                    )
                    val totalSum = mappedYearlyExpense.values.sum()

                    val totalExpenseList: List<TotalExpenseWithValue> =
                        mappedYearlyExpense.entries.map { (key, value) ->
                            TotalExpenseWithValue(key, value)
                        }
                    PieChartList(totalSum, totalExpenseList)
                } else {
                    EmptyDataComponent(modifier = Modifier.padding(8.dp))
                }
        }
    }
}

@Composable
fun PieAndBarChart(
    barChartData: List<TotalExpenseWithValue>,
    pieChartData: Map<String, Double>
) {
    Column {
        MpBarChart(
            modifier = Modifier
                .height(LocalConfiguration.current.screenHeightDp.dp / 4)
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp),
            expenseDetails = barChartData
        )

        MpPieChart(
            modifier = Modifier
                .height(LocalConfiguration.current.screenHeightDp.dp / 3)
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 12.dp),
            mappedExpense = pieChartData
        )
    }
}

@Composable
fun PieChartList(totalSum: Double, totalExpenseList: List<TotalExpenseWithValue>) {

    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier.padding(bottom = 32.dp)
    ) {
        items(totalExpenseList) { detail ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val imagePainter = getImageFromLabel(
                    getEnumByLocalizedLabel(
                        detail.value,
                        context
                    )?.name.orEmpty()
                )
                Image(
                    modifier = Modifier.size(44.dp),
                    painter = imagePainter,
                    contentDescription = detail.value
                )
                Text(
                    modifier = Modifier
                        .padding(start = 8.dp),
                    text = detail.value,
                    color = White,
                    style = MaterialTheme.typography.h5
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "% " + roundToDecimal(
                        value = ((detail.totalExpense.toFloat() / totalSum.toFloat()) * 100),
                        decimalPlaces = 2
                    ).toString(),
                    color = White,
                    style = MaterialTheme.typography.h5
                )
            }
        }
    }
}