package com.pixelcreative.saveable.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pixelcreative.saveable.core.Constants.Companion.EXPENSE_TABLE
import com.pixelcreative.saveable.core.getLocalDateAsString

@Entity(tableName = EXPENSE_TABLE)
data class Expense(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var date: String? = getLocalDateAsString(),
    var expenseDetailList: ExpenseDetailList?,
    var dailyTotalExpense: Double=0.0,
    var dailyTotalIncome: Double=0.0,
)
