package com.pixelcreative.saveable.screens.expenses

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pixelcreative.saveable.components.AddExpenseBottomSheet
import com.pixelcreative.saveable.components.DailyLimitCard
import com.pixelcreative.saveable.components.SpendType
import com.pixelcreative.saveable.components.SummaryCard
import com.pixelcreative.saveable.components.TotalBalanceCard
import com.pixelcreative.saveable.core.doubleOrZero
import com.pixelcreative.saveable.core.formatDoubleToString
import com.pixelcreative.saveable.core.getLocalDateAsString
import com.pixelcreative.saveable.core.getMonthAndYearAsString
import com.pixelcreative.saveable.core.getMonthlyExpenseTotal
import com.pixelcreative.saveable.core.getMonthlyIncomeTotal
import com.pixelcreative.saveable.domain.model.Expense
import com.pixelcreative.saveable.domain.model.ExpenseDetail
import com.pixelcreative.saveable.navigation.Router
import com.pixelcreative.saveable.ui.theme.BlackHtun
import com.pixelcreative.saveable.ui.theme.BluishPurple
import com.pixelcreative.saveable.ui.theme.BonusLevel
import com.pixelcreative.saveable.ui.theme.InvasiveIndigo
import com.pixelcreative.saveable.ui.theme.MediumSpringGreen
import com.pixelcreative.saveable.ui.theme.RadicalRed
import com.pixelcreative.saveable.ui.theme.White
import com.pixelcreative.saveable.ui.theme.ZimaBlue
import kotlinx.coroutines.launch

@Composable
@ExperimentalMaterialApi
fun ExpensesScreen(
    router: Router,
    hideBottomSheet: (Boolean) -> Unit
) {
    val expensesScreenViewModel: ExpensesScreenViewModel = hiltViewModel()

    expensesScreenViewModel.getDailyExpense(getLocalDateAsString())

    expensesScreenViewModel.getMonthlyExpense(getMonthAndYearAsString())

    val dailyExpense = expensesScreenViewModel.dailyExpense.collectAsState().value

    val monthlyExpense = expensesScreenViewModel.monthlyExpense.collectAsState().value

    val dailyLimit by rememberSaveable { mutableDoubleStateOf(500.00) }

    val sheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()

    var spendingType by remember { mutableStateOf(SpendType.None) }
    LaunchedEffect(sheetState.currentValue) {
        if (sheetState.currentValue == ModalBottomSheetValue.Hidden) {
            hideBottomSheet.invoke(true)
        }
    }
    ModalBottomSheetLayout(
        modifier = Modifier.navigationBarsPadding(),
        scrimColor = Color.Black.copy(alpha = 0.3f),
        sheetContent = {
            AddExpenseBottomSheet(spendType = spendingType)
        },
        sheetState = sheetState
    ) {
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
                        totalBalance = getMonthlyIncomeTotal(monthlyExpense).minus(
                            getMonthlyExpenseTotal(monthlyExpense)
                        ).formatDoubleToString(),
                        monthlyIncome = getMonthlyIncomeTotal(monthlyExpense).formatDoubleToString(),
                        monthlyExpense = getMonthlyExpenseTotal(monthlyExpense).formatDoubleToString()
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
                            limit = "$ " + dailyLimit.formatDoubleToString()
                        )

                        DailyLimitCard(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .padding(top = 12.dp),
                            colors = listOf(RadicalRed, BonusLevel),
                            title = "Remaining limit:",
                            limit = "$ " + dailyLimit.plus(
                                (dailyExpense?.dailyTotalIncome.doubleOrZero().minus(
                                    dailyExpense?.dailyTotalExpense.doubleOrZero()
                                ))
                            ).formatDoubleToString()
                        )
                    }
                }
            }

            SummaryCard(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 20.dp)
                    .weight(3.7f),
                recentExpense = getRecentExpense(dailyExpense),
                onSeeAllClicked = {
                    router.goToDetailScreen()
                    //Burda gidiyor fakat tekrar anasayfaya basınca anasayfaya dönmüyor sadece backpress yapınca dönüyor
                }
            )

            Row(
                modifier = Modifier
                    .weight(1.3f)
                    .padding(horizontal = 16.dp, vertical = 20.dp)
            ) {
                Column(
                    modifier = Modifier
                        .clickable {
                            scope.launch {
                                hideBottomSheet.invoke(false)
                                spendingType = SpendType.Expense
                                sheetState.show()

                            }
                        }
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
                        .clickable {
                            scope.launch {
                                hideBottomSheet.invoke(false)
                                spendingType = SpendType.Income
                                sheetState.show()
                            }
                        }
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
}

fun getRecentExpense(dailyExpense: Expense?): List<ExpenseDetail>? {
    return dailyExpense?.let { latestExpense ->
        latestExpense.expenseDetailList?.expenseDetail?.takeLast(4).let { detail ->
            detail?.takeLast(minOf(4, detail.size))
        }?.reversed()
    }
}
