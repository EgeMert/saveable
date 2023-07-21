package com.pixelcreative.saveable.screens.addexpense

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pixelcreative.saveable.components.AutoComplete
import com.pixelcreative.saveable.core.doubleOrZero
import com.pixelcreative.saveable.core.getLocalDateAsString
import com.pixelcreative.saveable.domain.model.ExpenseDetail
import com.pixelcreative.saveable.domain.model.IncomeDetail
import com.pixelcreative.saveable.navigation.Router
import com.pixelcreative.saveable.screens.expenses.ExpensesViewModel
import com.pixelcreative.saveable.ui.theme.Black
import com.pixelcreative.saveable.ui.theme.White
import com.pixelcreative.saveable.ui.theme.Zomp

@Composable
@ExperimentalMaterialApi
fun AddExpenseScreen(
    router: Router
) {
    val expensesViewModel: ExpensesViewModel = hiltViewModel()
    var userInput by rememberSaveable { mutableStateOf("") }
    var selectedCategory by rememberSaveable { mutableStateOf("") }

    val latestExpense by expensesViewModel.latestExpense.collectAsState(
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
            text = "Harcama girelim",
            color = White,
            style = MaterialTheme.typography.h2
        )

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                value = userInput,
                onValueChange = {
                    userInput = it
                },
                placeholder = {
                    Text(text = "Tutar buraya")
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Black,
                    focusedIndicatorColor = Zomp,
                    unfocusedIndicatorColor = Zomp,
                    cursorColor = Zomp,
                    textColor = Zomp,
                    placeholderColor = Zomp
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
            )

            val categories = listOf("Yeme-İçme", "Market", "Elektronik", "Evcil Hayvan")

            AutoComplete(
                categories = categories,
                onCategorySelected = { category ->
                    selectedCategory = category
                }
            )

            Button(
                onClick = {
                    latestExpense?.let { lastExpense ->
                        if (lastExpense.date != getLocalDateAsString()) {
                            expensesViewModel.addDailyExpense(
                                expenseAmount = userInput.toDouble(),
                                selectedCategory = selectedCategory
                            )
                        } else {
                            expensesViewModel.updateExpenseList(
                                expenseDetail = lastExpense.expenseDetailList?.expenseDetail?.plus(
                                    ExpenseDetail(
                                        price = userInput.toDouble(),
                                        category = selectedCategory
                                    )
                                ),
                                dailyTotalExpense =
                                (latestExpense?.dailyTotalExpense?.toDouble())?.plus(userInput.toDouble()).doubleOrZero()
                            )
                        }
                    } ?: run {
                        expensesViewModel.addDailyExpense(
                            expenseAmount = userInput.toDouble(),
                            selectedCategory = selectedCategory
                        )
                    }
                },
            ) {
                Text(
                    text = "Expense",
                    color = White,
                    style = MaterialTheme.typography.h1
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    latestExpense?.let { lastExpense ->
                        if (lastExpense.date != getLocalDateAsString()) {
                            expensesViewModel.addDailyIncome(
                                incomeAmount = userInput.toDouble(),
                                selectedCategory = selectedCategory
                            )
                        } else {
                            expensesViewModel.updateIncomeList(
                                incomeDetailList = lastExpense.incomeDetailList?.incomeDetail?.plus(
                                    IncomeDetail(
                                        price = userInput.toDouble(),
                                        category = selectedCategory
                                    )
                                ),
                                dailyTotalIncome =
                                (latestExpense?.dailyTotalIncome?.toDouble())?.plus(userInput.toDouble()).doubleOrZero()
                            )
                        }
                    } ?: run {
                        expensesViewModel.addDailyIncome(
                            incomeAmount = userInput.toDouble(),
                            selectedCategory = selectedCategory
                        )
                    }
                },
            ) {
                Text(
                    text = "Income",
                    color = White,
                    style = MaterialTheme.typography.h1
                )
            }
        }
    }
}
