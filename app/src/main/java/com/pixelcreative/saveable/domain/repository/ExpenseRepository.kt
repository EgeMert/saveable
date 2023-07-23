package com.pixelcreative.saveable.domain.repository

import com.pixelcreative.saveable.domain.model.Expense
import com.pixelcreative.saveable.domain.model.ExpenseDetail
import com.pixelcreative.saveable.domain.model.ExpenseDetailList
import com.pixelcreative.saveable.domain.model.IncomeDetailList
import kotlinx.coroutines.flow.Flow

typealias Expenses = List<Expense>
typealias ExpenseDetails = List<ExpenseDetail>

interface ExpenseRepository {
    //suspend fun getExpensesFromRoom(): Expenses

    suspend fun getExpenseFromRoom(id: Long): Expense

    suspend fun addExpenseToRoom(expense: Expense)

    suspend fun updateExpenseInRoom(expense: Expense)

    suspend fun deleteExpenseFromRoom(expense: Expense)

    suspend fun getDailyExpense(date: String): Expense?

    suspend fun getLatestExpense(): Expense?

    suspend fun updateDailyExpenseList(
        expenseDetailList: ExpenseDetailList,
        date: String,
        dailyTotalExpense: Double
    ): Int
    suspend fun updateDailyIncomeList(
        incomeDetailList: IncomeDetailList,
        date: String,
        dailyTotalIncome: Double
    ): Int
}

interface ExpenseDetailRepository {

    suspend fun getExpenseDetailsFromRoom(id: Long): Flow<ExpenseDetails>

    suspend fun getExpenseDetailsWithId(id: Long): Expense
}