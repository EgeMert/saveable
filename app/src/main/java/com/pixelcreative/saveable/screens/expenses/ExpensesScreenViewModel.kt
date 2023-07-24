package com.pixelcreative.saveable.screens.expenses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pixelcreative.saveable.core.getLocalDateAsString
import com.pixelcreative.saveable.domain.model.Expense
import com.pixelcreative.saveable.domain.model.ExpenseDetail
import com.pixelcreative.saveable.domain.model.ExpenseDetailList
import com.pixelcreative.saveable.domain.repository.ExpenseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExpensesScreenViewModel @Inject constructor(
    private val expenseRepository: ExpenseRepository
) : ViewModel() {

    private val _dailyExpense = MutableStateFlow<Expense?>(null)

    private val _latestExpense = MutableStateFlow<Expense?>(null)

    val dailyExpense: StateFlow<Expense?> get() = _dailyExpense

    val latestExpense: StateFlow<Expense?> get() = _latestExpense

    fun getLatestExpense() = viewModelScope.launch {
        _latestExpense.value = expenseRepository.getLatestExpense()
    }

    fun getDailyExpense(date: String) = viewModelScope.launch {
        _dailyExpense.value = expenseRepository.getDailyExpense(date)
    }

    fun updateExpenseList(
        expenseDetail: List<ExpenseDetail>?,
        dailyTotalExpense: Double,
        dailyTotalIncome: Double
    ) = viewModelScope.launch {

        val expenseDetailList = ExpenseDetailList(
            expenseDetail = expenseDetail
        )
        expenseRepository.updateDailyExpenseList(
            expenseDetailList,
            getLocalDateAsString(),
            dailyTotalExpense,
            dailyTotalIncome
        )
    }

    fun addDailyExpense(
        expenseAmount: Double,
        selectedCategory: String,
        incomeAmount: Double
    ) = viewModelScope.launch {
        val expense = Expense(
            date = getLocalDateAsString(),
            expenseDetailList = ExpenseDetailList(
                expenseDetail = listOf(
                    ExpenseDetail(
                        price = expenseAmount,
                        category = selectedCategory,
                        isIncome = false
                    )
                ),
            ),
            dailyTotalExpense = expenseAmount,
            dailyTotalIncome = incomeAmount
        )
        expenseRepository.addExpenseToRoom(expense)
    }
}