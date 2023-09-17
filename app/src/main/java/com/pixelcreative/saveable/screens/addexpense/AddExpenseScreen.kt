package com.pixelcreative.saveable.screens.addexpense

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.pixelcreative.saveable.components.DropDownMenu
import com.pixelcreative.saveable.components.DropDownType
import com.pixelcreative.saveable.components.SpendType
import com.pixelcreative.saveable.core.Constants
import com.pixelcreative.saveable.core.doubleOrZero
import com.pixelcreative.saveable.core.getLocalDateAsString
import com.pixelcreative.saveable.domain.model.ExpenseDetail
import com.pixelcreative.saveable.navigation.Router
import com.pixelcreative.saveable.screens.expenses.ExpensesScreenViewModel
import com.pixelcreative.saveable.ui.theme.BluishPurple
import com.pixelcreative.saveable.ui.theme.DarkPurple
import com.pixelcreative.saveable.ui.theme.OrangeCrush
import com.pixelcreative.saveable.ui.theme.RougeRed
import com.pixelcreative.saveable.ui.theme.White
import com.pixelcreative.saveable.ui.theme.ZimaBlue
import java.text.SimpleDateFormat
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@ExperimentalMaterialApi
fun AddExpenseScreen(
    router: Router?,
    spendType: String
) {
    val expensesScreenViewModel: ExpensesScreenViewModel = hiltViewModel()

    expensesScreenViewModel.getDailyExpense(getLocalDateAsString())
    val dailyExpense = expensesScreenViewModel.dailyExpense.collectAsState().value
    var selectedBillType by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("") }
    var dropDownType by remember { mutableStateOf(DropDownType.None) }
    val selectedList = remember { mutableStateListOf<String>() }
    var showDialog by remember { mutableStateOf(false) }
    var selectedNumber by remember { mutableStateOf("0") }
    var canShowDatePicker by remember { mutableStateOf(false) }
    var date by remember {
        mutableStateOf(getLocalDateAsString())
    }
    val screenHeight = LocalConfiguration.current.screenHeightDp / 2.5
    ConstraintLayout(
        modifier = Modifier
            .navigationBarsPadding()
            .padding(bottom = 8.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        if (canShowDatePicker) {
            val datePickerState = rememberDatePickerState(selectableDates = object :
                SelectableDates {
                override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                    return utcTimeMillis <= System.currentTimeMillis()
                }
            })

            val selectedDate = datePickerState.selectedDateMillis?.let {
                convertMillisToDate(it)
            } ?: ""
            DatePickerDialog(onDismissRequest = { canShowDatePicker = false }, confirmButton = {
                Button(onClick = {
                    date = selectedDate
                    canShowDatePicker = false
                }
                ) {
                    Text(text = "OK")
                }
                Log.d("date", "Selected Date--> $date")
            }, dismissButton = {
                Button(onClick = {
                    canShowDatePicker = false
                }) {
                    Text(text = "Cancel")
                }
            }) {
                DatePicker(
                    state = datePickerState
                )
            }
        }
        val (spendTypeRow, userInputView, userClickArea, dayText, spacerView) = createRefs()
        if (showDialog) {
            DropDownMenu(dropDownList = selectedList,
                dropDownType = dropDownType,
                onDismissAction = {
                    selectedCategory = Constants.EMPTY_STRING
                    selectedBillType = Constants.EMPTY_STRING
                },
                itemClickedAction = { item, type ->
                    showDialog = false
                    when (type) {
                        DropDownType.Bill -> {
                            selectedBillType = item
                        }

                        DropDownType.Categories -> {
                            selectedCategory = item
                        }

                        DropDownType.None -> {
                            return@DropDownMenu
                        }
                    }

                })
        }
        Row(
            Modifier
                .fillMaxWidth()
                .constrainAs(spendTypeRow) {
                    centerHorizontallyTo(parent)
                    top.linkTo(parent.top)
                    bottom.linkTo(userInputView.top)
                })
        {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                BluishPurple,
                                ZimaBlue,
                            )
                        )
                    )
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            dropDownType = DropDownType.Categories
                            selectedList.removeAll { true }
                            val billTypeList = listOf(
                                "Shopping",
                                "Clothes",
                                "Credit Card",
                                "Education",
                                "Electric",
                                "Electronic",
                                "Entertainment",
                                "Expense",
                                "Food",
                                "Furniture",
                                "Health",
                                "Income",
                                "Kids",
                                "Phone",
                                "Shopping",
                                "Rent",
                                "Transport",
                                "Wage",
                                "Pet",
                                "Water"
                            )
                            selectedList.addAll(billTypeList)
                            if (!showDialog) {
                                showDialog = true
                            }
                        }) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Category",
                            modifier = Modifier,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.body1,
                            color = White
                        )
                        Icon(
                            imageVector = Icons.Filled.ArrowDropDown,
                            contentDescription = null,
                            tint = White
                        )
                    }
                    Text(
                        text = selectedCategory,
                        modifier = Modifier.padding(bottom = 10.dp),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.body1,
                        color = White
                    )

                }
            }
        }
        Column(
            Modifier
                .fillMaxWidth()
                .constrainAs(userInputView) {
                    top.linkTo(spendTypeRow.bottom)
                    centerHorizontallyTo(parent)
                }) {
            Text(
                text = spendType,
                modifier = Modifier
                    .fillMaxWidth()

                    .padding(top = 10.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body1,
                color = White
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = selectedNumber,
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body1,
                color = White
            )
            Spacer(modifier = Modifier.height(10.dp))
            Divider(Modifier.fillMaxWidth(), color = White)
            Spacer(modifier = Modifier.height(10.dp))
        }
        Spacer(modifier = Modifier
            .height(screenHeight.dp)
            .constrainAs(spacerView) {
                top.linkTo(userInputView.bottom)
            })
        Row(
            Modifier
                .fillMaxWidth()
                .constrainAs(userClickArea) {
                    centerHorizontallyTo(parent)
                    top.linkTo(spacerView.bottom)
                    bottom.linkTo(dayText.top)
                })
        {
            val numberList = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", ".", "0", "okay")
            LazyVerticalGrid(
                columns = GridCells.Fixed(3), modifier = Modifier.weight(3f)
            ) {
                itemsIndexed(numberList) { index, items ->
                    if (items.contains("okay")) {
                        Box(
                            modifier = Modifier
                                .border(width = 0.3.dp, color = Color.Black)
                                .background(
                                    brush = Brush.horizontalGradient(
                                        colors = listOf(
                                            DarkPurple, RougeRed, OrangeCrush
                                        )
                                    )
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Check,
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(vertical = 23.dp, horizontal = 28.dp)
                                    .clickable {
                                        if (selectedNumber.isEmpty()) return@clickable
                                        if (spendType == SpendType.Expense.name) {
                                            dailyExpense?.let { expense ->
                                                if (expense.date != getLocalDateAsString()) {
                                                    expensesScreenViewModel.addDailyExpense(
                                                        expenseAmount = selectedNumber.toDouble(),
                                                        selectedCategory = selectedCategory,
                                                        incomeAmount = 0.0,
                                                        inputDate = date
                                                    )
                                                } else {
                                                    expensesScreenViewModel.updateExpenseList(
                                                        expenseDetail = expense.expenseDetailList?.expenseDetail?.plus(
                                                            ExpenseDetail(
                                                                price = selectedNumber.toDouble(),
                                                                category = selectedCategory,
                                                                isIncome = false,
                                                            )
                                                        ),
                                                        dailyTotalExpense =
                                                        dailyExpense.dailyTotalExpense
                                                            .plus(
                                                                selectedNumber.toDouble()
                                                            )
                                                            .doubleOrZero(),
                                                        dailyTotalIncome = dailyExpense.dailyTotalIncome
                                                    )
                                                }
                                            } ?: run {
                                                expensesScreenViewModel.addDailyExpense(
                                                    expenseAmount = selectedNumber.toDouble(),
                                                    selectedCategory = selectedCategory,
                                                    incomeAmount = 0.0,
                                                    inputDate = date
                                                )
                                            }
                                        } else {
                                            dailyExpense?.let { expense ->
                                                if (expense.date != getLocalDateAsString()) {
                                                    expensesScreenViewModel.addDailyExpense(
                                                        incomeAmount = selectedNumber.toDouble(),
                                                        selectedCategory = selectedCategory,
                                                        expenseAmount = 0.0,
                                                        inputDate = date
                                                    )
                                                } else {
                                                    expensesScreenViewModel.updateExpenseList(
                                                        expenseDetail = expense.expenseDetailList?.expenseDetail?.plus(
                                                            ExpenseDetail(
                                                                price = selectedNumber.toDouble(),
                                                                category = selectedCategory,
                                                                isIncome = true
                                                            )
                                                        ),
                                                        dailyTotalExpense = dailyExpense.dailyTotalExpense,
                                                        dailyTotalIncome =
                                                        dailyExpense.dailyTotalIncome
                                                            .plus(selectedNumber.toDouble())
                                                            .doubleOrZero()
                                                    )
                                                }
                                            } ?: run {
                                                expensesScreenViewModel.addDailyExpense(
                                                    incomeAmount = selectedNumber.toDouble(),
                                                    selectedCategory = selectedCategory,
                                                    expenseAmount = 0.0
                                                )
                                            }
                                        }
                                        selectedNumber = "0"

                                    },
                                tint = White
                            )
                        }
                    } else {
                        Box(
                            modifier = Modifier
                                .background(Color.White)
                                .border(width = 0.3.dp, color = Color.Black)
                                .clickable {
                                    if (selectedNumber == "0") selectedNumber =
                                        Constants.EMPTY_STRING
                                    selectedNumber += items
                                }, contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = items,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 24.dp, horizontal = 28.dp),
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.body1,
                                color = Color.Black
                            )
                        }
                    }

                }
            }
            Column(modifier = Modifier.weight(1f)) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp)
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    DarkPurple, RougeRed, OrangeCrush
                                )
                            )
                        )

                        .clickable {
                            if (selectedNumber.length > 1) {
                                val removedNumber = selectedNumber.dropLast(1)
                                selectedNumber = removedNumber
                            } else {
                                selectedNumber = "0"
                            }
                        }, contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = null,
                        modifier = Modifier,
                        tint = White
                    )
                }
                Divider(Modifier.fillMaxWidth(), color = White)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp)
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    DarkPurple, RougeRed, OrangeCrush
                                )
                            )
                        ), contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.DateRange,
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            canShowDatePicker = true
                        },
                        tint = White
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Red)
                .constrainAs(dayText) {
                    centerHorizontallyTo(parent)
                    bottom.linkTo(parent.bottom)
                }, contentAlignment = Alignment.Center
        ) {
            Text(
                text = date,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(vertical = 12.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.subtitle2,
                color = White
            )
        }
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Preview(showBackground = true, showSystemUi = true, backgroundColor = 0xFF000000)
@Composable
fun AddExpenseScreenPreview() {
    AddExpenseScreen(null, "Income")
}

private fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy")
    return formatter.format(Date(millis))
}
