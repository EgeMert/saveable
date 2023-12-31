package com.pixelcreative.saveable.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.pixelcreative.saveable.R
import com.pixelcreative.saveable.components.analysis.DailyExpensesAnalysis
import com.pixelcreative.saveable.components.analysis.MonthlyExpenseAnalysis
import com.pixelcreative.saveable.components.analysis.YearlyExpenseAnalysis
import com.pixelcreative.saveable.navigation.Router
import com.pixelcreative.saveable.ui.theme.BlackHtun
import com.pixelcreative.saveable.ui.theme.BluishPurple
import com.pixelcreative.saveable.ui.theme.White

@Composable
@ExperimentalMaterialApi
fun DetailScreen(
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

        Column {
            when (selectedTabIndex) {
                0 -> DailyExpensesAnalysis()

                1 -> MonthlyExpenseAnalysis()

                2 -> YearlyExpenseAnalysis()
            }
        }
    }
}
