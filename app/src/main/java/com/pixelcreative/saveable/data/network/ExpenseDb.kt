package com.pixelcreative.saveable.data.network

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pixelcreative.saveable.data.ExpenseDbConverter
import com.pixelcreative.saveable.data.dao.ExpenseDao
import com.pixelcreative.saveable.domain.model.Expense

@TypeConverters(value = [ExpenseDbConverter::class])
@Database(
    entities = [Expense::class],
    version = 1,
    exportSchema = false
)
abstract class ExpenseDb : RoomDatabase() {
    abstract val expenseDao: ExpenseDao
}