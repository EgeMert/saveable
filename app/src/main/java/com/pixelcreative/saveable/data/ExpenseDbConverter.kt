package com.pixelcreative.saveable.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.pixelcreative.saveable.domain.model.ExpenseDetailList
import com.pixelcreative.saveable.domain.model.IncomeDetailList

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
class IncomeDbConverter {
    @TypeConverter
    fun fromIncomeToJson(incomeDetailList: IncomeDetailList?):String{
        return Gson().toJson(incomeDetailList)
    }
    @TypeConverter
    fun fromJsonToIncome(json: String):IncomeDetailList?{
        return Gson().fromJson(json, IncomeDetailList::class.java)
    }

}