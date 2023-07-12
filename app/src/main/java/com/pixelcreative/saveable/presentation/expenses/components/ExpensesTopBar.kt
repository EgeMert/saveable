package com.pixelcreative.saveable.presentation.expenses.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.pixelcreative.saveable.R

@Composable
fun ExpensesTopBar(
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        modifier = modifier,
        backgroundColor = Color(0xFFF5F5F5),
        contentPadding = PaddingValues(0.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Image(
                    modifier = Modifier.padding(vertical = 8.dp),
                    painter = painterResource(id = R.drawable.ic_saveable_logo),
                    contentDescription = null
                )
            }
        }
    }
}