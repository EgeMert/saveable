package com.pixelcreative.saveable.domain.model

import java.time.LocalDate

data class ExpenseDetailList(
    val expenseDetail:List<ExpenseDetail>?
)

data class ExpenseDetail(
    val id: String? = LocalDate.now().toString(),
    val price: Double,
    val isIncome: Boolean,
    val category: String
)