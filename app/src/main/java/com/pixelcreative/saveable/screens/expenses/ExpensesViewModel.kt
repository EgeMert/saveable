package com.pixelcreative.saveable.screens.expenses

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pixelcreative.saveable.core.Constants.Companion.EMPTY_STRING
import com.pixelcreative.saveable.domain.model.Expense
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

    fun getExpense(id: Long) = viewModelScope.launch {
        expense = repo.getExpenseFromRoom(id)
    }

    fun addExpense(expense: Expense) = viewModelScope.launch {
        repo.addExpenseToRoom(expense)
    }

    fun updateExpense(expense: Expense) = viewModelScope.launch {
        repo.updateExpenseInRoom(expense)
    }

    fun deleteExpense(expense: Expense) = viewModelScope.launch {
        repo.deleteExpenseFromRoom(expense)
    }

    fun updateTitle(dailyTotalExpense: String) {
        expense = expense.copy(
            dailyTotalExpense = dailyTotalExpense
        )
    }

    fun updateAuthor(date: String) {
        expense = expense.copy(
            date = date
        )
    }

    fun openDialog() {
        openDialog = true
    }

    fun closeDialog() {
        openDialog = false
    }
}