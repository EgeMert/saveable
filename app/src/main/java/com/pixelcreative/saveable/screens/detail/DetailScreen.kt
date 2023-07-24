package com.pixelcreative.saveable.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pixelcreative.saveable.components.ExpenseDetailListView
import com.pixelcreative.saveable.core.doubleOrZero
import com.pixelcreative.saveable.core.formatDoubleToString
import com.pixelcreative.saveable.core.getLocalDateAsString
import com.pixelcreative.saveable.navigation.Router
import com.pixelcreative.saveable.screens.expenses.ExpensesScreenViewModel
import com.pixelcreative.saveable.ui.theme.BlackHtun
import com.pixelcreative.saveable.ui.theme.BluishPurple
import com.pixelcreative.saveable.ui.theme.Pinball
import com.pixelcreative.saveable.ui.theme.White

@Composable
@ExperimentalMaterialApi
fun DetailScreen(
    router: Router
) {
    val expensesScreenViewModel: ExpensesScreenViewModel = hiltViewModel()

    expensesScreenViewModel.getDailyExpense(getLocalDateAsString())

    val dailyExpense = expensesScreenViewModel.dailyExpense.collectAsState().value

    var selectedTabIndex by remember { mutableIntStateOf(0) }

    val tabNames = listOf("Daily", "Monthly", "Yearly")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BlackHtun)
    ) {
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

        LazyColumn {
            when (selectedTabIndex) {
                0 -> item {
                    Column(
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        Text(
                            modifier = Modifier.padding(top = 20.dp),
                            text = "Total balance",
                            color = Pinball,
                            style = MaterialTheme.typography.h5
                        )

                        Text(
                            modifier = Modifier.padding(top = 12.dp),
                            text = "$ " + (dailyExpense?.dailyTotalIncome.doubleOrZero().minus(
                                dailyExpense?.dailyTotalExpense.doubleOrZero()
                            )).formatDoubleToString(),
                            color = Pinball,
                            style = MaterialTheme.typography.h3
                        )

                        Text(
                            modifier = Modifier
                                .padding(top = 20.dp),
                            text = "Daily ( ${dailyExpense?.expenseDetailList?.expenseDetail?.size} )",
                            color = Pinball,
                            style = MaterialTheme.typography.h6
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
                                .padding(vertical = 20.dp),
                            expenseDetailList = dailyExpense?.expenseDetailList?.expenseDetail?.reversed()
                        )
                    }

                }

                1 -> item { Text("Sekme 2 içeriği") }
                2 -> item { Text("Sekme 3 içeriği") }
            }
        }
    }
}
