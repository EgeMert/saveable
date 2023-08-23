package com.pixelcreative.saveable.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import com.pixelcreative.saveable.core.Constants.Companion.EMPTY_STRING

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseScaffold(
    modifier: Modifier,
    icon: ImageVector = Icons.Default.ArrowBack,
    secondIcon: ImageVector = Icons.Default.Settings,
    scaffoldContent: @Composable() () -> Unit = {},
    textStyle: TextStyle = MaterialTheme.typography.titleMedium,
    isIconVisible: Boolean = true,
    isSecondIconVisible: Boolean = false,
    isExpanded: Boolean = false,
    dropDownItemClick: () -> Unit = {},
    title: String = EMPTY_STRING,
    iconClick: () -> Unit,
    secondIconClick: () -> Unit = {},
    snackBarContent: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        snackbarHost = { snackBarContent.invoke() },
        modifier = modifier.fillMaxSize(),
        topBar = {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (isIconVisible) {
                    IconButton(onClick = { iconClick.invoke() }) {
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            modifier = Modifier,
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = title,
                    style = textStyle,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.weight(2f))
                scaffoldContent.invoke()
                if (isSecondIconVisible) {
                    IconButton(
                        onClick = { secondIconClick.invoke() },
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(
                            imageVector = secondIcon,
                            contentDescription = null,
                            modifier = Modifier,
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                        DropdownMenu(
                            modifier = Modifier.wrapContentSize(),
                            expanded = isExpanded,
                            onDismissRequest = { /*TODO*/ },
                            properties = PopupProperties(
                                dismissOnClickOutside = true,
                                dismissOnBackPress = true
                            )
                        ) {

                            DropdownMenuItem(
                                onClick = {
                                    dropDownItemClick.invoke()
                                },
                                text = {
                                    Text(
                                        text = "Delete",
                                        color = MaterialTheme.colorScheme.onBackground,
                                        style = MaterialTheme.typography.labelMedium,
                                        modifier = Modifier.fillMaxWidth(),
                                        textAlign = TextAlign.Center
                                    )
                                }
                            )

                        }
                    }
                }

            }


        }, containerColor = MaterialTheme.colorScheme.background
    ) {
        content.invoke(it)

    }
}


