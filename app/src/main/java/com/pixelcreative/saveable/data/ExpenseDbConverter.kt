package com.pixelcreative.saveable.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.pixelcreative.saveable.domain.model.ExpenseDetailList

class ExpenseDbConverter {
    @TypeConverter
    fun fromExpenseToJson(expenseDetailList: ExpenseDetailList):String{
        return Gson().toJson(expenseDetailList)
    }
    @TypeConverter
    fun fromJsonToExpense(json: String):ExpenseDetailList?{
        return Gson().fromJson(json, ExpenseDetailList::class.java)
    }

}