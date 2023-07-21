package com.pixelcreative.saveable.screens.expenses

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pixelcreative.saveable.core.Constants.Companion.EMPTY_STRING
import com.pixelcreative.saveable.core.getLocalDateAsString
import com.pixelcreative.saveable.domain.model.Expense
import com.pixelcreative.saveable.domain.model.ExpenseDetail
import com.pixelcreative.saveable.domain.model.ExpenseDetailList
import com.pixelcreative.saveable.domain.repository.ExpenseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExpensesViewModel @Inject constructor(
    private val repo: ExpenseRepository
) : ViewModel() {

    var expense by mutableStateOf(
        Expense(
            id = 0,
            date = EMPTY_STRING,
            expenseDetailList = null,
            dailyTotalExpense = EMPTY_STRING
        )
    )
        private set

    var openDialog by mutableStateOf(false)

    val latestExpense = repo.getLatestExpense()

    val expenses = repo.getExpensesFromRoom()

    var dailyExpense by mutableStateOf(
        Expense(
            id = 0,
            date = EMPTY_STRING,
            expenseDetailList = null,
            dailyTotalExpense = EMPTY_STRING
        )
    )

    fun getDailyExpense(date: String) = viewModelScope.launch {
        dailyExpense = repo.getDailyExpense(date)
    }

    fun updateExpenseList(
        expenseDetail: List<ExpenseDetail>?,
        dailyTotalExpense: String
    ) = viewModelScope.launch {

        val expenseDetailList = ExpenseDetailList(
            expenseDetail = expenseDetail
        )
        repo.updateExpenseList(expenseDetailList, getLocalDateAsString(), dailyTotalExpense)
    }

    fun addExpense(expenseAmount: String, selectedCategory: String) = viewModelScope.launch {

        val expense = Expense(
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
        repo.addExpenseToRoom(expense)
    }

    fun updateExpense(expense: Expense) = viewModelScope.launch {
        repo.updateExpenseInRoom(expense)
    }

    fun deleteExpense(expense: Expense) = viewModelScope.launch {
        repo.deleteExpenseFromRoom(expense)
    }
}