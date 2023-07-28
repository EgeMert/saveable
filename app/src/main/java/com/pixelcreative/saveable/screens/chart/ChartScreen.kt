package com.pixelcreative.saveable.screens.chart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pixelcreative.saveable.components.BarGraph
import com.pixelcreative.saveable.components.BarType
import com.pixelcreative.saveable.components.PieChart
import com.pixelcreative.saveable.core.doubleOrZero
import com.pixelcreative.saveable.core.getExpenseSummaryByCategory
import com.pixelcreative.saveable.core.getLocalDateAsString
import com.pixelcreative.saveable.core.getMonthAndYearAsString
import com.pixelcreative.saveable.core.getYearAsString
import com.pixelcreative.saveable.domain.model.ExpenseDetail
import com.pixelcreative.saveable.navigation.Router
import com.pixelcreative.saveable.screens.expenses.ExpensesScreenViewModel
import com.pixelcreative.saveable.ui.theme.BlackHtun
import com.pixelcreative.saveable.ui.theme.BluishPurple
import com.pixelcreative.saveable.ui.theme.White
import com.pixelcreative.saveable.ui.theme.Zomp
import kotlin.math.abs

@Composable
@ExperimentalMaterialApi
fun ChartScreen(
    router: Router
) {

    var selectedTabIndex by remember { mutableIntStateOf(0) }

    val tabNames = listOf("Daily", "Monthly", "Yearly")

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
                    dailyExpense?.expenseDetailList?.expenseDetail?.let {
                        Column(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            PieChart(
                                data = convertToMap(it)
                            )
                        }
                    }

                1 ->
                    monthlyExpense?.let { monthlyData ->
                        val monthlyExpenseSummary = getExpenseSummaryByCategory(monthlyData)
                        Column(
                            modifier = Modifier.fillMaxSize()
                        ) {

                            Column(
                                modifier = Modifier
                                    .padding(horizontal = 30.dp)
                                   ,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {

                                val dataList = convertPriceToMutableList(monthlyExpenseSummary)
                                val floatValue = mutableListOf<Float>()
                                val datesList = convertCategoryToMutableList(monthlyExpenseSummary)

                                dataList.forEachIndexed { index, value ->
                                    floatValue.add(
                                        index = index,
                                        element = value.toFloat() / dataList.max().toFloat()
                                    )
                                }

                                BarGraph(
                                    graphBarData = floatValue,
                                    xAxisScaleData = datesList,
                                    barData = dataList,
                                    height = 300.dp,
                                    roundType = BarType.TOP_CURVED,
                                    barWidth = 20.dp,
                                    barColor = Zomp,
                                    barArrangement = Arrangement.SpaceEvenly
                                )
                                PieChart(
                                    data = convertToMap(monthlyExpenseSummary)
                                )
                            }
                        }
                    }

                2 ->
                    yearlyExpense?.let {
                        Column(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            PieChart(
                                data = convertToMap(getExpenseSummaryByCategory(it))
                            )
                        }
                    }
            }
        }
}

fun convertToMap(expenseDetails: List<ExpenseDetail>): Map<String, Double> {
    return expenseDetails.mapNotNull { detail ->
        detail.category?.let { category -> category to abs(detail.price.doubleOrZero()) }
    }.toMap()
}

fun convertPriceToMutableList(expenseDetailList: List<ExpenseDetail>?): MutableList<Double> {
    val priceList = mutableListOf<Double>()

    expenseDetailList?.forEach { expenseDetail ->
        priceList.add(expenseDetail.price.doubleOrZero())
    }

    return priceList
}

fun convertCategoryToMutableList(expenseDetailList: List<ExpenseDetail>?): MutableList<String> {
    val priceList = mutableListOf<String>()

    expenseDetailList?.forEach { expenseDetail ->
        priceList.add(expenseDetail.category.orEmpty())
    }

    return priceList
}
