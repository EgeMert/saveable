package com.pixelcreative.saveable.core

import com.pixelcreative.saveable.domain.model.Expense
import com.pixelcreative.saveable.domain.model.ExpenseDetail
import com.pixelcreative.saveable.domain.model.ExpenseDetailList

class Constants {
    companion object {
        //Room
        const val EXPENSE_TABLE = "expense_table"
        const val EXPENSE_DETAIL_TABLE = "expense_detail_table"

        //Screens
        const val EXPENSES_SCREEN = "Expenses"
        const val UPDATE_EXPENSES_SCREEN = "Update expense"
        const val DETAIL_SCREEN = "Details of expense"

        //Arguments
        const val EXPENSE_ID = "expenseId"

        //Actions
        const val ADD_EXPENSE = "Add a expense."
        const val DELETE_EXPENSE = "Delete a expense."

        //Buttons
        const val ADD_BUTTON = "Add"
        const val DISMISS_BUTTON = "Dismiss"
        const val UPDATE_BUTTON = "Update"

        //Placeholders
        const val EXPENSE_DATE = "Type a expense date ..."
        const val DAILY_TOTAL_EXPENSE = "Type dailyTotalExpense ..."
        const val EMPTY_STRING = ""

        //Basket Screen
        const val SPENT_TODAY_TITLE = "You've saved"
        const val SPENT_TODAY = "Today's summary"

        val dummyExpense = Expense(
            expenseDetailList = ExpenseDetailList(
                expenseDetail = listOf(
                    ExpenseDetail(
                        price = 50.0,
                        category = "Food"
                    ),
                    ExpenseDetail(
                        price = 40.0,
                        category = "Shopping"
                    ),
                    ExpenseDetail(
                        price = 30.0,
                        category = "Transport"
                    ),
                    ExpenseDetail(
                        price = 20.0,
                        category = "Health"
                    )
                )
            ),
            dailyTotalExpense = 0.0,
            dailyTotalIncome = 50.0
        )
    }
}