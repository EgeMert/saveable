package com.pixelcreative.saveable.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.pixelcreative.saveable.core.TotalExpenseWithValue
import com.pixelcreative.saveable.core.doubleToFloat
import com.pixelcreative.saveable.ui.theme.BondyBlue
import com.pixelcreative.saveable.ui.theme.White

@Composable
fun MpBarChart(
    modifier: Modifier = Modifier,
    expenseDetails: List<TotalExpenseWithValue>
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            BarChart(context)
        },
        update = { barChart ->
            val entries = expenseDetails.mapIndexed { index, expenseDetail ->
                BarEntry(
                    index.toFloat(),
                    expenseDetail.totalExpense.doubleToFloat(),
                )
            }

            val dataSet = BarDataSet(entries, TOTAL_EXPENSE).apply {
                color = BondyBlue.toArgb()
            }

            val barData = BarData(dataSet).apply {

            }

            barChart.apply {
                xAxis.setValueFormatter(IndexAxisValueFormatter(expenseDetails.map { it.value }))
                axisLeft.axisMinimum = 0.0f
                axisRight.isEnabled = false
                description.isEnabled = false
                xAxis.textColor = White.toArgb()
                axisLeft.textColor = White.toArgb()
                legend.textColor = White.toArgb()
                data = barData
                invalidate()
            }
        }
    )
}

const val TOTAL_EXPENSE = "Toplam harcama"
