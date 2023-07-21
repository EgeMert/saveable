package com.pixelcreative.saveable.domain.model

import java.time.LocalDate

data class ExpenseDetailList(
    val expenseDetail: List<ExpenseDetail>?
)
data class IncomeDetailList(
    val incomeDetail: List<IncomeDetail>?
)

data class ExpenseDetail(
    val id: String? = LocalDate.now().toString() + (System.currentTimeMillis().toString()),
    val price: Double?,
    val category: String?
)
data class IncomeDetail(
    val id: String? = LocalDate.now().toString() + (System.currentTimeMillis().toString()),
    val price: Double?,
    val category: String?
)