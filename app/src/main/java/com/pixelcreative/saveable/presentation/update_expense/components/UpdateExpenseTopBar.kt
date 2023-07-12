package com.pixelcreative.saveable.presentation.update_expense.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import com.pixelcreative.saveable.core.Constants.Companion.UPDATE_EXPENSES_SCREEN

@Composable
fun UpdateExpenseTopBar(
    navigateBack: () -> Unit
) {
    TopAppBar (
        title = {
            Text(
                text = UPDATE_EXPENSES_SCREEN
            )
        },
        navigationIcon = {
            IconButton(
                onClick = navigateBack
            ) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = null,
                )
            }
        }
    )
}