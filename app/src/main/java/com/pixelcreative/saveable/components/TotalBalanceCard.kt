package com.pixelcreative.saveable.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pixelcreative.saveable.ui.theme.Pinball
import com.pixelcreative.saveable.ui.theme.White

@Composable
fun TotalBalanceCard(
    modifier: Modifier = Modifier,
    colors: List<Color>
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(brush = Brush.horizontalGradient(colors = colors))
    ){
        Column(
            modifier = Modifier.padding(12.dp)
        ) {

            Text(
                text = "Total Balance",
                color = Pinball,
                style = MaterialTheme.typography.subtitle1
            )

            Text(
                text = "$ 23,970.30",
                color = White,
                style = MaterialTheme.typography.h5
            )
        }
    }
}