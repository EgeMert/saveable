package com.pixelcreative.saveable.data.repository

import com.pixelcreative.saveable.data.dao.ExpenseDao
import com.pixelcreative.saveable.domain.model.Expense
import com.pixelcreative.saveable.domain.model.ExpenseDetailList
import com.pixelcreative.saveable.domain.repository.ExpenseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ExpenseRepositoryImpl(
    private val expenseDao: ExpenseDao
) : ExpenseRepository {

    /*    override suspend fun getExpensesFromRoom() = withContext(Dispatchers.IO) {
            expenseDao.getExpenses()
        }*/

    override suspend fun getExpenseFromRoom(id: Long) = withContext(Dispatchers.IO) {
        expenseDao.getExpense(id)
    }

    override suspend fun addExpenseToRoom(expense: Expense) = withContext(Dispatchers.IO) {
        expenseDao.addExpense(expense)
    }

    override suspend fun updateExpenseInRoom(expense: Expense) = withContext(Dispatchers.IO) {
        expenseDao.updateExpense(expense)
    }

    override suspend fun deleteExpenseFromRoom(expense: Expense) = withContext(Dispatchers.IO) {
        expenseDao.deleteExpense(expense)
    }

    override suspend fun getDailyExpense(date: String) = withContext(Dispatchers.IO) {
        expenseDao.getDailyExpense(date)
    }

    override suspend fun getMonthlyExpense(monthAndYear: String) =
        withContext(Dispatchers.IO) {
            expenseDao.getMonthlyExpense(monthAndYear)
        }

    override suspend fun getLatestExpense() = withContext(Dispatchers.IO) {
        expenseDao.getLatestExpense()
    }

    override suspend fun updateDailyExpenseList(
        expenseDetailList: ExpenseDetailList,
        date: String,
        dailyTotalExpense: Double,
        dailyTotalIncome: Double
    ) = withContext(Dispatchers.IO) {
        expenseDao.updateExpenseList(expenseDetailList, date, dailyTotalExpense, dailyTotalIncome)
    }

}
