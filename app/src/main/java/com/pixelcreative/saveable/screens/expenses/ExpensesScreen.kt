package com.pixelcreative.saveable.screens.expenses

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pixelcreative.saveable.components.TotalBalanceCard
import com.pixelcreative.saveable.navigation.Router
import com.pixelcreative.saveable.ui.theme.BluishPurple
import com.pixelcreative.saveable.ui.theme.ZimaBlue

@Composable
@ExperimentalMaterialApi
fun ExpensesScreen(
    router: Router
) {
    val viewModel: ExpensesViewModel = hiltViewModel()

    Row(
        modifier = Modifier.padding(12.dp)
    ) {
        TotalBalanceCard(
            modifier = Modifier.size(160.dp),
            colors = listOf(ZimaBlue, BluishPurple)
        )
    }

}