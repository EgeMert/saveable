package com.pixelcreative.saveable.core

import com.pixelcreative.saveable.domain.model.Expense
import com.pixelcreative.saveable.domain.model.ExpenseDetail
import java.text.DecimalFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter


fun Double?.doubleOrZero(): Double {
    return this ?: 0.0
}

fun Int?.intOrZero(): Int {
    return this ?: 0
}

fun getLocalDateAsString(currentDate:LocalDate = LocalDate.now()): String {
    val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return currentDate.format(dateFormatter)
}

fun getMonthAndYearAsString(currentDate:LocalDate = LocalDate.now()): String {
    val dateFormatter = DateTimeFormatter.ofPattern("MM-yyyy")
    return currentDate.format(dateFormatter)
}

fun getYearAsString(currentDate:LocalDate = LocalDate.now()): String {
    val dateFormatter = DateTimeFormatter.ofPattern("yyyy")
    return currentDate.format(dateFormatter)
}

fun String?.orNone(): String {
    return this ?: "-"
}

fun Double?.formatDoubleToString(): String {
    val decimalFormat = DecimalFormat("#.##")
    return decimalFormat.format(this ?: 0.0)
}

fun Float?.nonNullable(): Float {
    return this ?: 0.0f
}

fun Double?.doubleToFloat(): Float {
    return this?.toFloat() ?: 0.0f
}

fun String?.stringToFloat(): Float {
    return this?.toFloat() ?: 0.0f
}

fun getExpenseSummaryByCategory(expenseList: List<Expense>?): List<ExpenseDetail> {
    val categoryMap = mutableMapOf<String, Double>()
    expenseList?.let {

        for (expense in expenseList) {
            expense.expenseDetailList?.expenseDetail?.forEach { detail ->
                val category = detail.category ?: "Unknown"
                val price = detail.price ?: 0.0

                val currentTotal = categoryMap.getOrDefault(category, 0.0)
                categoryMap[category] =
                    if (detail.isIncome == false) currentTotal + price else currentTotal - price
            }
        }
    }
    val summaryList = mutableListOf<ExpenseDetail>()
    categoryMap.forEach { (category, totalExpense) ->
        summaryList.add(
            ExpenseDetail(
                category = category, price = totalExpense, isIncome = totalExpense < 0
            )
        )
    }

    return summaryList
}

fun getTotalPrice(expenses: List<Expense>?): Double {
    var totalPrice = 0.0
    expenses?.let { expenseList ->
        for (expense in expenseList) {
            expense.expenseDetailList?.expenseDetail?.forEach { detail ->
                if (detail.isIncome == true) {
                    totalPrice += detail.price ?: 0.0
                } else {
                    totalPrice -= detail.price ?: 0.0
                }
            }
        }
    }

    return totalPrice
}

fun getMonthlyIncomeTotal(expenses: List<Expense>?): Double {
    var incomeTotal = 0.0
    expenses?.let { expenseList ->
        for (expense in expenseList) {
            expense.expenseDetailList?.expenseDetail?.forEach { detail ->
                if (detail.isIncome == true) {
                    incomeTotal += detail.price.doubleOrZero()
                }
            }
        }
    }
    return incomeTotal
}

fun getMonthlyExpenseTotal(expenses: List<Expense>?): Double {
    var expenseTotal = 0.0
    expenses?.let { expenseList ->
        for (expense in expenseList) {
            expense.expenseDetailList?.expenseDetail?.forEach { detail ->
                if (detail.isIncome == false) {
                    expenseTotal += detail.price.doubleOrZero()
                }
            }
        }
    }
    return expenseTotal
}

data class MonthlyTotalExpense(
    val month: String,
    val totalExpense: Double
)

fun calculateMonthlyTotalExpenses(expenseList: List<Expense>?): List<MonthlyTotalExpense> {
    if (expenseList == null) {
        return emptyList()
    }

    val monthlyTotalMap = mutableMapOf<Int, Double>()

    for (expense in expenseList) {
        val month = LocalDate.parse(expense.date, DateTimeFormatter.ofPattern("yyyy-MM-dd")).monthValue

        val currentTotal = monthlyTotalMap.getOrDefault(month, 0.0)
        val expenseDetailTotal = expense.expenseDetailList?.expenseDetail?.sumByDouble { it.price.doubleOrZero() } ?: 0.0

        monthlyTotalMap[month] = currentTotal + expenseDetailTotal
    }

    return monthlyTotalMap.entries.map { (month, total) ->
        MonthlyTotalExpense(month.toString(), total)
    }
}