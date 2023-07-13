package com.pixelcreative.saveable.screens.addexpense

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pixelcreative.saveable.navigation.Router
import com.pixelcreative.saveable.screens.expenses.ExpensesViewModel
import com.pixelcreative.saveable.ui.theme.Black
import com.pixelcreative.saveable.ui.theme.PhilippineGray
import com.pixelcreative.saveable.ui.theme.White
import com.pixelcreative.saveable.ui.theme.Zomp

@Composable
@ExperimentalMaterialApi
fun AddExpenseScreen(
    router: Router
) {
    val viewModel: ExpensesViewModel = hiltViewModel()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Black)
    ) {
        Text(
            modifier = Modifier
                .padding(
                    top = 24.dp,
                    start = 16.dp,
                    end = 16.dp
                ),
            text = "Harcama girelim",
            color = White,
            style = MaterialTheme.typography.h2
        )

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            var text by rememberSaveable { mutableStateOf("") }

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                value = text,
                onValueChange = {
                    text = it
                },
                placeholder = {
                    Text(text = "Tutar buraya")
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Black,
                    focusedIndicatorColor = Zomp,
                    unfocusedIndicatorColor = Zomp,
                    cursorColor = Zomp,
                    textColor = Zomp,
                    placeholderColor = Zomp
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
            )

            val categories = listOf("Yeme-İçme", "Market", "Elektronik", "Evcil Hayvan")

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(8.dp)
            ) {
                categories.forEach { category ->
                    Box(
                        modifier = Modifier
                            .background(Black)
                            .padding(8.dp)
                            .border(
                                width = 1.dp,
                                shape = RoundedCornerShape(8.dp),
                                color = PhilippineGray
                            )
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(8.dp),
                            text = category,
                            color = White,
                            style = MaterialTheme.typography.h6
                        )
                    }
                }
            }
        }
    }
}
