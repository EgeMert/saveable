package com.pixelcreative.saveable.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.pixelcreative.saveable.ui.theme.EerieBlack

@Composable
fun DropDownMenu(
    dropDownList: List<String>,
    dropDownType: DropDownType,
    onDismissAction:()->Unit,
    itemClickedAction: (String, DropDownType) -> Unit
) {
    Dialog(
        onDismissRequest = { onDismissAction.invoke() },
        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
    ) {
        LazyColumn(
            Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(EerieBlack)
        ) {
            items(dropDownList) { item ->
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()

                        .padding(16.dp)
                        .clickable {
                            itemClickedAction.invoke(item, dropDownType)
                        }) {
                    Text(
                        text = item,
                        style = MaterialTheme.typography.subtitle1,
                        color = Color.White
                    )
                }
            }
        }
    }
}

enum class DropDownType {
    Bill, Categories, None
}

@Preview
@Composable
fun DropDownMenu_Prev() {
    val list = listOf("Cash", "Card", "Saving")
    DropDownMenu(list, DropDownType.Bill, onDismissAction = {}) { item, type -> }
}