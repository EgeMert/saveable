package com.pixelcreative.saveable.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pixelcreative.saveable.core.Constants.Companion.EMPTY_STRING
import com.pixelcreative.saveable.ui.theme.BluishPurple
import com.pixelcreative.saveable.ui.theme.DarkPurple
import com.pixelcreative.saveable.ui.theme.OrangeCrush
import com.pixelcreative.saveable.ui.theme.RougeRed
import com.pixelcreative.saveable.ui.theme.White
import com.pixelcreative.saveable.ui.theme.ZimaBlue

@Composable
fun AddExpenseContent(spendType: SpendType) {
    var selectedBillType by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("") }
    var dropDownType by remember { mutableStateOf(DropDownType.None) }
    val selectedList = remember { mutableStateListOf<String>() }
    var showDialog by remember { mutableStateOf(false) }
    var selectedNumber by remember { mutableStateOf("0") }

    Column(modifier = Modifier.height(540.dp)) {
        if (showDialog) {
            DropDownMenu(
                dropDownList = selectedList,
                dropDownType = dropDownType,
                onDismissAction = {
                    selectedNumber = "0"
                    selectedCategory = EMPTY_STRING
                    selectedBillType = EMPTY_STRING
                },
                itemClickedAction = { item, type ->
                    showDialog = false
                    when (type) {
                        DropDownType.Bill -> {
                            selectedBillType = item
                        }

                        DropDownType.Categories -> {
                            selectedCategory = item
                        }

                        DropDownType.None -> {
                            return@DropDownMenu
                        }
                    }

                })
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.Center
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
                    ),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            selectedList.removeAll { true }
                            dropDownType = DropDownType.Bill
                            val billTypeList = listOf(
                                "Card",
                                "Cash",
                                "Saving"
                            )
                            selectedList.addAll(billTypeList)
                            if (!showDialog) {
                                showDialog = true
                            }
                        }
                ) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Bill",
                            modifier = Modifier,
                            style = MaterialTheme.typography.body1,
                            color = White
                        )
                        Icon(
                            imageVector = Icons.Filled.ArrowDropDown,
                            contentDescription = null,
                            tint = White
                        )
                    }

                    Text(
                        text = selectedBillType,
                        modifier = Modifier
                            .padding(bottom = 10.dp),
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
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            dropDownType = DropDownType.Categories
                            selectedList.removeAll { true }
                            val billTypeList = listOf(
                                "Shopping",
                                "Clothes",
                                "Credit Card",
                                "Education",
                                "Electric",
                                "Electronic",
                                "Entertainment",
                                "Expense",
                                "Food",
                                "Furniture",
                                "Health",
                                "Income",
                                "Kids",
                                "Phone",
                                "Shopping",
                                "Rent",
                                "Transport",
                                "Wage",
                                "Pet",
                                "Water"
                            )
                            selectedList.addAll(billTypeList)
                            if (!showDialog) {
                                showDialog = true
                            }
                        }
                ) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Category",
                            modifier = Modifier,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.body1,
                            color = White
                        )
                        Icon(
                            imageVector = Icons.Filled.ArrowDropDown,
                            contentDescription = null,
                            tint = White
                        )
                    }
                    Text(
                        text = selectedCategory,
                        modifier = Modifier
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
                text = spendType.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body1,
                color = White
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = selectedNumber,
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
                .fillMaxHeight()
                .weight(3.78f)
        ) {
            val numberList = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", ",", "0", "okay")
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier
                    .weight(3f)
                    .fillMaxHeight()

            ) {
                itemsIndexed(numberList) { index, items ->
                    if (items.contains("okay")) {
                        Box(
                            modifier = Modifier
                                .border(width = 0.3.dp, color = Color.Black)
                                .size(70.dp)
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
                    } else {
                        Box(
                            modifier = Modifier
                                .background(Color.White)
                                .border(width = 0.3.dp, color = Color.Black)
                                .size(70.dp)
                                .clickable {
                                    if (selectedNumber == "0") selectedNumber = EMPTY_STRING
                                    selectedNumber += items
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = items,
                                modifier = Modifier
                                    .fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.body1,
                                color = Color.Black
                            )
                        }
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
                        .weight(1f)
                        .clickable {
                            if (selectedNumber.length > 1) {
                                val removedNumber =
                                    selectedNumber.dropLast(1)
                                selectedNumber = removedNumber
                            } else {
                                selectedNumber = "0"
                            }
                        }, contentAlignment = Alignment.Center
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
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(BluishPurple)
                .weight(0.5f), contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Sunday, 23 - July - 2023",
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.subtitle2,
                color = White
            )
        }

    }

}

enum class SpendType {
    Expense, Income, None
}

@Preview(device = "id:pixel_7_pro")
@Composable
fun AddExpenseContentPreview() {
    AddExpenseContent(SpendType.None)
}
