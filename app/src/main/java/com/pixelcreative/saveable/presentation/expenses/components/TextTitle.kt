package com.pixelcreative.saveable.presentation.expenses.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun TextTitle(
    expenseTitle: String
) {
    Text(
        text = expenseTitle,
        color = Color.DarkGray,
        fontSize = 25.sp
    )
}