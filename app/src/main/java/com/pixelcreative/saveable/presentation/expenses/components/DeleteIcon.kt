package com.pixelcreative.saveable.presentation.expenses.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import com.pixelcreative.saveable.core.Constants.Companion.DELETE_EXPENSE

@Composable
fun DeleteIcon(
    deleteExpense: () -> Unit
) {
    IconButton(
        onClick = deleteExpense
    ) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = DELETE_EXPENSE,
        )
    }
}