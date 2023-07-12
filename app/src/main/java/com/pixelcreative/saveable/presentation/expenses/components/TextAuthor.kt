package com.pixelcreative.saveable.presentation.expenses.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp

@Composable
fun TextAuthor(
    expenseAuthor: String
) {
    Text(
        text = "by $expenseAuthor",
        color = Color.DarkGray,
        fontSize = 12.sp,
        textDecoration = TextDecoration.Underline
    )
}