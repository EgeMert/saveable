package com.pixelcreative.saveable.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.pixelcreative.saveable.R
import com.pixelcreative.saveable.ui.theme.White

@Composable
fun EmptyDataComponent(
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = stringResource(id = R.string.no_expense_text),
        color = White,
        style = MaterialTheme.typography.h3
    )
}
