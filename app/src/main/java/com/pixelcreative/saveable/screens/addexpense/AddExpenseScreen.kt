package com.pixelcreative.saveable.screens.addexpense

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.pixelcreative.saveable.core.getLocalDateAsString
import com.pixelcreative.saveable.domain.model.Expense
import com.pixelcreative.saveable.domain.model.ExpenseDetail
import com.pixelcreative.saveable.domain.model.ExpenseDetailList
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
    var expenseAmount by rememberSaveable { mutableStateOf("") }
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
                value = expenseAmount,
                onValueChange = {
                    expenseAmount = it
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

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    latestExpense?.let { lastExpense ->
                        if (lastExpense.date != getLocalDateAsString()) {
                            expensesViewModel.addExpense(
                                Expense(
                                    date = getLocalDateAsString(),
                                    expenseDetailList = ExpenseDetailList(
                                        expenseDetail = listOf(
                                            ExpenseDetail(
                                                price = expenseAmount.toDouble(),
                                                isIncome = false,
                                                category = selectedCategory
                                            )
                                        )
                                    ),
                                    dailyTotalExpense = expenseAmount
                                )
                            )
                        } else {
                            expensesViewModel.updateExpenseList(
                                expenseDetailList = ExpenseDetailList(
                                    expenseDetail = latestExpense?.expenseDetailList?.expenseDetail?.plus(
                                        ExpenseDetail(
                                            price = expenseAmount.toDouble(),
                                            isIncome = false,
                                            category = selectedCategory
                                        )
                                    )
                                ),
                                date = getLocalDateAsString(),
                                dailyTotalExpense =
                                (latestExpense?.dailyTotalExpense?.toDouble())?.plus(expenseAmount.toDouble())
                                    .toString()
                            )
                        }
                    } ?: run {
                        expensesViewModel.addExpense(
                            Expense(
                                date = getLocalDateAsString(),
                                expenseDetailList = ExpenseDetailList(
                                    expenseDetail = listOf(
                                        ExpenseDetail(
                                            price = expenseAmount.toDouble(),
                                            isIncome = false,
                                            category = selectedCategory
                                        )
                                    )
                                ),
                                dailyTotalExpense = expenseAmount
                            )
                        )
                    }
                },
            ) {
                Text(
                    text = "Kaydet",
                    color = Zomp,
                    style = MaterialTheme.typography.h1
                )
            }
        }
    }
}
