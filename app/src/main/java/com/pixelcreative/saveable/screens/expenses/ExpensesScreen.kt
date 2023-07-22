package com.pixelcreative.saveable.screens.expenses

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pixelcreative.saveable.components.DailyLimitCard
import com.pixelcreative.saveable.components.SummaryCard
import com.pixelcreative.saveable.components.TotalBalanceCard
import com.pixelcreative.saveable.navigation.Router
import com.pixelcreative.saveable.ui.theme.BlackHtun
import com.pixelcreative.saveable.ui.theme.BluishPurple
import com.pixelcreative.saveable.ui.theme.BonusLevel
import com.pixelcreative.saveable.ui.theme.InvasiveIndigo
import com.pixelcreative.saveable.ui.theme.MediumSpringGreen
import com.pixelcreative.saveable.ui.theme.RadicalRed
import com.pixelcreative.saveable.ui.theme.White
import com.pixelcreative.saveable.ui.theme.ZimaBlue

@Composable
@ExperimentalMaterialApi
fun ExpensesScreen(
    router: Router
) {
    val expensesViewModel: ExpensesViewModel = hiltViewModel()

    val latestExpense by expensesViewModel.latestExpense.collectAsState(
        initial = null
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BlackHtun)
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 20.dp)
                .weight(2f)
        ) {
            Row {
                TotalBalanceCard(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1.6f),
                    colors = listOf(ZimaBlue, BluishPurple),
                    totalBalance = "$ 23,970.30",
                    monthlyIncome = "$ 5,235.25",
                    monthlyExpense = "$ 3,710.80"
                )

                Column(
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .weight(1.4f)
                ) {

                    DailyLimitCard(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                        colors = listOf(InvasiveIndigo, MediumSpringGreen),
                        title = "Daily limit:",
                        limit = "$ 500"
                    )

                    DailyLimitCard(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .padding(top = 12.dp),
                        colors = listOf(RadicalRed, BonusLevel),
                        title = "Remaining limit:",
                        limit = "$ 150"
                    )
                }
            }
        }

        SummaryCard(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 20.dp)
                .weight(3.7f),
            latestExpense = latestExpense
        )

        Row(
            modifier = Modifier
                .weight(1.3f)
                .padding(horizontal = 16.dp, vertical = 20.dp)
        ) {
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(RadicalRed, BonusLevel)
                        )
                    )
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier.padding(12.dp),
                    text = "Expense",
                    color = White,
                    style = MaterialTheme.typography.h5,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.width(20.dp))

            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(InvasiveIndigo, MediumSpringGreen)
                        )
                    )
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier.padding(12.dp),
                    text = "Income",
                    color = White,
                    style = MaterialTheme.typography.h5,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
