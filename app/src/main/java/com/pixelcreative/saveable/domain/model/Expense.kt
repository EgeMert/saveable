package com.pixelcreative.saveable.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pixelcreative.saveable.core.Constants.Companion.EXPENSE_TABLE

@Entity(tableName = EXPENSE_TABLE)
data class Expense(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var date: String,
    @ColumnInfo(name = "expenseDetailList")
    var expenseDetailList: ExpenseDetailList?,
    var dailyTotalExpense: String
)
