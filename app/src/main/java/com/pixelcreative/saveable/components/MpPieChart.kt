package com.pixelcreative.saveable.components

import android.graphics.Color
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.pixelcreative.saveable.core.Constants
import com.pixelcreative.saveable.ui.theme.BlackHtun
import com.pixelcreative.saveable.ui.theme.BondyBlue
import com.pixelcreative.saveable.ui.theme.White

@Composable
fun MpPieChart(
    mappedExpense: Map<String, Double>
) {
    AndroidView(
        modifier = Modifier
            .height(360.dp)
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 12.dp),
        factory = { context ->
            PieChart(context)
        },
        update = { pieChart ->

            val totalSum = mappedExpense.values.sum()

            val entries = mappedExpense.map { entry ->
                PieEntry(
                    (entry.value.toFloat() / totalSum.toFloat()) * 100,
                    entry.key
                )
            }

            val dataSet = PieDataSet(entries, Constants.EMPTY_STRING).apply {
                color = BondyBlue.toArgb()
            }

            val pieData = PieData(dataSet).apply {

            }

            val colors: MutableList<Int> = ArrayList()
            for (i in entries.indices) {
                colors.add(getRandomColor())
            }

            pieChart.apply {
                dataSet.colors = colors
                setDrawEntryLabels(false)
                dataSet.valueTextSize = 16f
                description.isEnabled = false
                setHoleColor(BlackHtun.toArgb())
                legend.textColor = White.toArgb()
                legend.isWordWrapEnabled = true
                data = pieData
                invalidate()
            }
        }
    )
}

fun getRandomColor(): Int {
    val alpha = 255
    val red = (0..255).random()
    val green = (0..255).random()
    val blue = (0..255).random()

    return Color.argb(alpha, red, green, blue)
}