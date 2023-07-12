package com.pixelcreative.saveable.data.repository

import com.pixelcreative.saveable.data.dao.ExpenseDao
import com.pixelcreative.saveable.data.dao.ExpenseDetailDao
import com.pixelcreative.saveable.domain.model.Expense
import com.pixelcreative.saveable.domain.repository.ExpenseDetailRepository
import com.pixelcreative.saveable.domain.repository.ExpenseRepository

class ExpenseRepositoryImpl(
    private val expenseDao: ExpenseDao
) : ExpenseRepository {

    override fun getExpensesFromRoom() = expenseDao.getExpenses()

    override suspend fun getExpenseFromRoom(id: Long) = expenseDao.getExpense(id)

    override suspend fun addExpenseToRoom(expense: Expense) = expenseDao.addExpense(expense)

    override suspend fun updateExpenseInRoom(expense: Expense) = expenseDao.updateExpense(expense)

    override suspend fun deleteExpenseFromRoom(expense: Expense) = expenseDao.deleteExpense(expense)

    override fun getLatestExpense() = expenseDao.getLatestExpense()
}

class ExpenseDetailRepositoryImpl(
    private val expenseDetailDao: ExpenseDetailDao
) : ExpenseDetailRepository {

    override fun getExpenseDetailsFromRoom(id: Long) = expenseDetailDao.getExpenseDetails(id)

    override suspend fun getExpenseDetailsWithId(id: Long): Expense {
        TODO("Not yet implemented")
    }

}