package com.pixelcreative.saveable.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pixelcreative.saveable.core.orNone
import com.pixelcreative.saveable.ui.theme.InvasiveIndigo
import com.pixelcreative.saveable.ui.theme.MediumSpringGreen
import com.pixelcreative.saveable.ui.theme.Pinball
import com.pixelcreative.saveable.ui.theme.White

@Preview
@Composable
fun DailyLimitCard_Preview() {
    DailyLimitCard(
        modifier = Modifier.defaultMinSize(minWidth = 160.dp, minHeight = 70.dp),
        colors = listOf(InvasiveIndigo, MediumSpringGreen),
        title = "Daily spending limit:",
        limit = "$500"
    )
}

@Preview
@Composable
fun DailyLimitCardNull_Preview() {
    DailyLimitCard(
        modifier = Modifier.defaultMinSize(minWidth = 160.dp, minHeight = 70.dp),
        colors = listOf(InvasiveIndigo, MediumSpringGreen),
        title = "Daily spending limit:",
    )
}

@Composable
fun DailyLimitCard(
    modifier: Modifier = Modifier,
    colors: List<Color>,
    title: String? = null,
    limit: String? = null
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(brush = Brush.horizontalGradient(colors = colors))
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = title.orEmpty(),
            color = Pinball,
            style = MaterialTheme.typography.subtitle1,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            text = limit.orNone(),
            color = White,
            style = MaterialTheme.typography.h5,
            textAlign = TextAlign.Center
        )
    }
}
