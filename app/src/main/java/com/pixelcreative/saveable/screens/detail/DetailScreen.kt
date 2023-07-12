package com.pixelcreative.saveable.screens.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.pixelcreative.saveable.navigation.Router

@Composable
fun DetailScreen(router: Router) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Detail Screen",
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.colors.onPrimary,
            modifier = Modifier.clickable {
                router.goToExpenseScreen()
            }
        )
    }
}