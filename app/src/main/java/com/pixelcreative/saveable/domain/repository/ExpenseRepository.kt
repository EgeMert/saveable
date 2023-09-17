package com.pixelcreative.saveable.domain.repository

import com.pixelcreative.saveable.domain.model.Expense
import com.pixelcreative.saveable.domain.model.ExpenseDetail
import com.pixelcreative.saveable.domain.model.ExpenseDetailList

typealias Expenses = List<Expense>
typealias ExpenseDetails = List<ExpenseDetail>

interface ExpenseRepository {

    suspend fun getExpenseFromRoom(id: Long): Expense

    suspend fun addExpenseToRoom(expense: Expense)

    suspend fun updateExpenseInRoom(expense: Expense)

    suspend fun deleteExpenseFromRoom(expense: Expense)

    suspend fun getDailyExpense(date: String): Expense?

    suspend fun getMonthlyExpense(monthAndYear: String): List<Expense>?

    suspend fun getYearlyExpense(year: String): List<Expense>?

    suspend fun getLatestExpense(): Expense?

    suspend fun updateDailyExpenseList(
        expenseDetailList: ExpenseDetailList,
        date: String,
        dailyTotalExpense: Double,
        dailyTotalIncome: Double
    ): Int
}
