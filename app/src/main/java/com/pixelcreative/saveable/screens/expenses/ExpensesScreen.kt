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
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.rememberDatePickerState
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pixelcreative.saveable.R
import com.pixelcreative.saveable.components.AddExpenseBottomSheet
import com.pixelcreative.saveable.components.DailyLimitCard
import com.pixelcreative.saveable.components.SpendType
import com.pixelcreative.saveable.components.SummaryCard
import com.pixelcreative.saveable.components.TotalBalanceCard
import com.pixelcreative.saveable.core.Constants.Companion.EMPTY_STRING
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
import java.text.SimpleDateFormat
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
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
    var canShowDatePicker by remember { mutableStateOf(false) }
    var date by remember {
        mutableStateOf(EMPTY_STRING)
    }
    val sheetState =
        rememberModalBottomSheetState(
            initialValue = ModalBottomSheetValue.Hidden,
            skipHalfExpanded = true
        )
    val scope = rememberCoroutineScope()
    expensesScreenViewModel.getDailyExpense(getLocalDateAsString())

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
            if (canShowDatePicker) {
                val datePickerState = rememberDatePickerState(selectableDates = object :
                    SelectableDates {
                    override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                        return utcTimeMillis <= System.currentTimeMillis()
                    }
                })

                val selectedDate = datePickerState.selectedDateMillis?.let {
                    convertMillisToDate(it)
                } ?: EMPTY_STRING
                DatePickerDialog(onDismissRequest = { canShowDatePicker = false }, confirmButton = {
                    date = selectedDate
                }) {
                    DatePicker(
                        state = datePickerState
                    )
                }
            }
            AddExpenseBottomSheet(
                spendType = spendingType,
                addDate = {
                    canShowDatePicker = true
                }
            ) { userInput, selectedCategory, selectedBillType ->
                if (spendingType == SpendType.Expense) {
                    dailyExpense?.let { expense ->
                        if (expense.date != getLocalDateAsString()) {
                            expensesScreenViewModel.addDailyExpense(
                                expenseAmount = userInput.toDouble(),
                                selectedCategory = selectedCategory,
                                incomeAmount = 0.0
                            )
                        } else {
                            expensesScreenViewModel.updateExpenseList(
                                expenseDetail = expense.expenseDetailList?.expenseDetail?.plus(
                                    ExpenseDetail(
                                        price = userInput.toDouble(),
                                        category = selectedCategory,
                                        isIncome = false
                                    )
                                ),
                                dailyTotalExpense =
                                dailyExpense.dailyTotalExpense.plus(userInput.toDouble())
                                    .doubleOrZero(),
                                dailyTotalIncome = dailyExpense.dailyTotalIncome
                            )
                        }
                    } ?: run {
                        expensesScreenViewModel.addDailyExpense(
                            expenseAmount = userInput.toDouble(),
                            selectedCategory = selectedCategory,
                            incomeAmount = 0.0
                        )
                    }
                } else {
                    dailyExpense?.let { expense ->
                        if (expense.date != getLocalDateAsString()) {
                            expensesScreenViewModel.addDailyExpense(
                                incomeAmount = userInput.toDouble(),
                                selectedCategory = selectedCategory,
                                expenseAmount = 0.0
                            )
                        } else {
                            expensesScreenViewModel.updateExpenseList(
                                expenseDetail = expense.expenseDetailList?.expenseDetail?.plus(
                                    ExpenseDetail(
                                        price = userInput.toDouble(),
                                        category = selectedCategory,
                                        isIncome = true
                                    )
                                ),
                                dailyTotalExpense = dailyExpense.dailyTotalExpense,
                                dailyTotalIncome =
                                dailyExpense.dailyTotalIncome.plus(userInput.toDouble())
                                    .doubleOrZero()
                            )
                        }
                    } ?: run {
                        expensesScreenViewModel.addDailyExpense(
                            incomeAmount = userInput.toDouble(),
                            selectedCategory = selectedCategory,
                            expenseAmount = 0.0
                        )
                    }
                }
                scope.launch {
                    sheetState.hide()

                }
            }
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
                        totalBalance = stringResource(
                            id = R.string.total_balance_info,
                            getMonthlyIncomeTotal(monthlyExpense).minus(
                                getMonthlyExpenseTotal(monthlyExpense)
                            ).formatDoubleToString()
                        ),
                        monthlyIncome = stringResource(
                            id = R.string.total_balance_info,
                            getMonthlyIncomeTotal(monthlyExpense).formatDoubleToString()
                        ),
                        monthlyExpense = stringResource(
                            id = R.string.total_balance_info,
                            getMonthlyExpenseTotal(monthlyExpense).formatDoubleToString()
                        )
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
                            title = stringResource(id = R.string.daily_spending_limit_title),
                            limit = stringResource(
                                id = R.string.total_balance_info,
                                dailyLimit.formatDoubleToString()
                            )
                        )

                        DailyLimitCard(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .padding(top = 12.dp),
                            colors = listOf(RadicalRed, BonusLevel),
                            title = stringResource(id = R.string.remaining_limit_title),
                            limit = stringResource(
                                id = R.string.total_balance_info,
                                dailyLimit.plus(
                                    (dailyExpense?.dailyTotalIncome.doubleOrZero().minus(
                                        dailyExpense?.dailyTotalExpense.doubleOrZero()
                                    ))
                                ).formatDoubleToString()
                            )
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
//
//            Row(
//                modifier = Modifier
//                    .weight(1.3f)
//                    .padding(horizontal = 16.dp, vertical = 20.dp)
//            ) {
//                Column(
//                    modifier = Modifier
//                        .clickable {
//                            scope.launch {
//                                hideBottomSheet.invoke(false)
//                                spendingType = SpendType.Expense
//                                // sheetState.show()
//                                router.goToAddExpenseScreen(SpendType.Expense.name)
//
//                            }
//                        }
//                        .clip(RoundedCornerShape(8.dp))
//                        .background(
//                            brush = Brush.horizontalGradient(
//                                colors = listOf(RadicalRed, BonusLevel)
//                            )
//                        )
//                        .weight(1f),
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    verticalArrangement = Arrangement.Center
//                ) {
//                    Text(
//                        modifier = Modifier.padding(12.dp),
//                        text = stringResource(id = R.string.expense),
//                        color = White,
//                        style = MaterialTheme.typography.h5,
//                        textAlign = TextAlign.Center
//                    )
//                }
//
//                Spacer(modifier = Modifier.width(20.dp))
//
//                Column(
//                    modifier = Modifier
//                        .clickable {
//                            scope.launch {
//                                hideBottomSheet.invoke(false)
//                                spendingType = SpendType.Income
//                                // sheetState.show()
//                                router.goToAddExpenseScreen(SpendType.Income.name)
//                            }
//                        }
//                        .clip(RoundedCornerShape(8.dp))
//                        .background(
//                            brush = Brush.horizontalGradient(
//                                colors = listOf(InvasiveIndigo, MediumSpringGreen)
//                            )
//                        )
//                        .weight(1f),
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    verticalArrangement = Arrangement.Center
//                ) {
//                    Text(
//                        modifier = Modifier.padding(12.dp),
//                        text = stringResource(id = R.string.income),
//                        color = White,
//                        style = MaterialTheme.typography.h5,
//                        textAlign = TextAlign.Center
//                    )
//                }
//            }
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

private fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy")
    return formatter.format(Date(millis))
}
