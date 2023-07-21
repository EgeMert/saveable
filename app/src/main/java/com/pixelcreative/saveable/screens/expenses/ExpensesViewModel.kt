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
import com.pixelcreative.saveable.domain.model.IncomeDetail
import com.pixelcreative.saveable.domain.model.IncomeDetailList
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
            incomeDetailList = null
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
            incomeDetailList = null
        )
    )

    fun getDailyExpense(date: String) = viewModelScope.launch {
        dailyExpense = repo.getDailyExpense(date)
    }

    fun updateExpenseList(
        expenseDetail: List<ExpenseDetail>?,
        dailyTotalExpense: Double
    ) = viewModelScope.launch {

        val expenseDetailList = ExpenseDetailList(
            expenseDetail = expenseDetail
        )
        repo.updateDailyExpenseList(expenseDetailList, getLocalDateAsString(), dailyTotalExpense)
    }
    fun updateIncomeList(
        incomeDetailList: List<IncomeDetail>?,
        dailyTotalIncome: Double
    ) = viewModelScope.launch {
        val incomeDetailList = IncomeDetailList(
            incomeDetail  = incomeDetailList
        )
        repo.updateDailyIncomeList(incomeDetailList, getLocalDateAsString(), dailyTotalIncome)
    }

    fun addDailyExpense(expenseAmount: Double, selectedCategory: String) = viewModelScope.launch {
        val expense = Expense(
            date = getLocalDateAsString(),
            expenseDetailList = ExpenseDetailList(
                expenseDetail = listOf(
                    ExpenseDetail(
                        price = expenseAmount,
                        category = selectedCategory,
                    )
                ),
            ),
            dailyTotalExpense = expenseAmount,
            dailyTotalIncome = 0.0,
            incomeDetailList = IncomeDetailList(emptyList())
        )
        repo.addExpenseToRoom(expense)
    }
    fun addDailyIncome(incomeAmount: Double, selectedCategory: String) = viewModelScope.launch {
        val expense = Expense(
            date = getLocalDateAsString(),
            incomeDetailList = IncomeDetailList(
                incomeDetail = listOf(
                    IncomeDetail(
                        price = incomeAmount,
                        category = selectedCategory,
                    )
                ),
            ),
            dailyTotalExpense = 0.0,
            dailyTotalIncome = 0.0,
            expenseDetailList = ExpenseDetailList(emptyList())
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