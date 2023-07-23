package com.pixelcreative.saveable.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pixelcreative.saveable.ui.theme.BluishPurple
import com.pixelcreative.saveable.ui.theme.DarkPurple
import com.pixelcreative.saveable.ui.theme.OrangeCrush
import com.pixelcreative.saveable.ui.theme.RougeRed
import com.pixelcreative.saveable.ui.theme.White
import com.pixelcreative.saveable.ui.theme.ZimaBlue


@Composable
fun AddExpenseContent(selectedBillType: String, selectedCategory: String) {
    val desiredHeight = LocalConfiguration.current.screenHeightDp
    Column(modifier = Modifier.size((desiredHeight / 1.3).dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                DarkPurple,
                                RougeRed,
                                OrangeCrush
                            )
                        )
                    )
            ) {
                Column {
                    Text(
                        text = "Bill",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.body1,
                        color = White
                    )
                    Text(
                        text = selectedBillType,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.body1,
                        color = White
                    )
                }
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                BluishPurple,
                                ZimaBlue,
                            )
                        )
                    )
            ) {
                Column {
                    Text(
                        text = "Category",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.body1,
                        color = White
                    )
                    Text(
                        text = selectedCategory,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.body1,
                        color = White
                    )
                }
            }
        }
        Column(
            modifier = Modifier
                .background(BluishPurple)
                .weight(2f), verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Expense ( \$ )",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body1,
                color = White
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "0",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body1,
                color = White
            )
            Spacer(modifier = Modifier.height(10.dp))
            Divider(Modifier.fillMaxWidth(), color = White)
            Box(modifier = Modifier.fillMaxHeight(), contentAlignment = Alignment.Center) {
                Text(
                    text = "Explanation",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.body1,
                    color = White
                )
            }
        }
        Row(
            modifier = Modifier
                .weight(5f)
                .fillMaxHeight()
        ) {
            val numberList = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", ",", "0")
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(3f)
            ) {
                items(numberList) { number ->
                    Box(
                        modifier = Modifier
                            .background(Color.White)
                            .border(width = 0.3.dp, color = Color.Black)
                            .size(92.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = number,
                            modifier = Modifier
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.body1,
                            color = Color.Black
                        )
                    }
                }
            }
            Column(modifier = Modifier.weight(1f)) {
                Box(
                    modifier = Modifier
                        .height(70.dp)
                        .fillMaxWidth()
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    DarkPurple,
                                    RougeRed,
                                    OrangeCrush
                                )
                            )
                        )
                        .weight(1f), contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = null,
                        modifier = Modifier,
                        tint = White
                    )
                }
                Divider(Modifier.fillMaxWidth(), color = White)
                Box(
                    modifier = Modifier
                        .height(70.dp)
                        .fillMaxWidth()
                        .weight(1f)
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    DarkPurple,
                                    RougeRed,
                                    OrangeCrush
                                )
                            )
                        ), contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.DateRange,
                        contentDescription = null,
                        modifier = Modifier,
                        tint = White
                    )
                }
                Divider(Modifier.fillMaxWidth(), color = White)
                Box(
                    modifier = Modifier
                        .height(70.dp)
                        .fillMaxWidth()
                        .weight(1.3f)
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    DarkPurple,
                                    RougeRed,
                                    OrangeCrush
                                )
                            )
                        ), contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = null,
                        modifier = Modifier,
                        tint = White
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(BluishPurple)
                .weight(0.9f), contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Sunday, 23 - July - 2023",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body1,
                color = White
            )
        }

    }

}

@Preview
@Composable
fun AddExpenseContentPreview() {
    AddExpenseContent("Cash", "Shopping")
}
