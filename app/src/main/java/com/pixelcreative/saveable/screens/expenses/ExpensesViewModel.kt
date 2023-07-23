package com.pixelcreative.saveable.screens.expenses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pixelcreative.saveable.core.getLocalDateAsString
import com.pixelcreative.saveable.domain.model.Expense
import com.pixelcreative.saveable.domain.model.ExpenseDetail
import com.pixelcreative.saveable.domain.model.ExpenseDetailList
import com.pixelcreative.saveable.domain.model.IncomeDetail
import com.pixelcreative.saveable.domain.model.IncomeDetailList
import com.pixelcreative.saveable.domain.repository.ExpenseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExpensesViewModel @Inject constructor(
    private val expenseRepository: ExpenseRepository
) : ViewModel() {

    private val initialExpense = Expense(
        date = null,
        expenseDetailList = null,
        incomeDetailList = null
    )

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
        dailyTotalExpense: Double
    ) = viewModelScope.launch {

        val expenseDetailList = ExpenseDetailList(
            expenseDetail = expenseDetail
        )
        expenseRepository.updateDailyExpenseList(expenseDetailList, getLocalDateAsString(), dailyTotalExpense)
    }

    fun updateIncomeList(
        incomeDetailList: List<IncomeDetail>?,
        dailyTotalIncome: Double
    ) = viewModelScope.launch {
        val incomeDetailList = IncomeDetailList(
            incomeDetail = incomeDetailList
        )
        expenseRepository.updateDailyIncomeList(
            incomeDetailList,
            getLocalDateAsString(),
            dailyTotalIncome
        )
    }

    fun addDailyExpense(expenseAmount: Double, selectedCategory: String) = viewModelScope.launch {
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
            dailyTotalIncome = 0.0,
            incomeDetailList = IncomeDetailList(emptyList())
        )
        expenseRepository.addExpenseToRoom(expense)
    }

    fun addDailyIncome(incomeAmount: Double, selectedCategory: String) = viewModelScope.launch {
        val expense = Expense(
            date = getLocalDateAsString(),
            incomeDetailList = IncomeDetailList(
                incomeDetail = listOf(
                    IncomeDetail(
                        price = incomeAmount,
                        category = selectedCategory,
                        isIncome = true
                    )
                ),
            ),
            dailyTotalExpense = 0.0,
            dailyTotalIncome = 0.0,
            expenseDetailList = ExpenseDetailList(emptyList()),
        )
        expenseRepository.addExpenseToRoom(expense)
    }
}