package com.pixelcreative.saveable.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.IGNORE
import androidx.room.Query
import androidx.room.Update
import com.pixelcreative.saveable.core.Constants.Companion.EXPENSE_DETAIL_TABLE
import com.pixelcreative.saveable.core.Constants.Companion.EXPENSE_TABLE
import com.pixelcreative.saveable.domain.model.Expense
import com.pixelcreative.saveable.domain.repository.ExpenseDetails
import com.pixelcreative.saveable.domain.repository.Expenses
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {
    @Query("SELECT * FROM $EXPENSE_TABLE ORDER BY id ASC")
    fun getExpenses(): Flow<Expenses>

    @Query("SELECT * FROM $EXPENSE_TABLE WHERE id = :id")
    suspend fun getExpense(id: Long): Expense

    @Insert(onConflict = IGNORE)
    suspend fun addExpense(expense: Expense)

    @Update
    suspend fun updateExpense(expense: Expense)

    @Delete
    suspend fun deleteExpense(expense: Expense)

    @Query("SELECT * FROM $EXPENSE_TABLE ORDER BY id DESC LIMIT 1")
    fun getLatestExpense(): Flow<Expense>
}

@Dao
interface ExpenseDetailDao {

    @Query("SELECT * FROM $EXPENSE_DETAIL_TABLE WHERE mainEntityId = :mainEntityId")
    fun getExpenseDetails(mainEntityId: Long): Flow<ExpenseDetails>
}