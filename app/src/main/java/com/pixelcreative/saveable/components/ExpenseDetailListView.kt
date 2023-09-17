package com.pixelcreative.saveable.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pixelcreative.saveable.R
import com.pixelcreative.saveable.core.Constants.Companion.EMPTY_STRING
import com.pixelcreative.saveable.core.doubleOrZero
import com.pixelcreative.saveable.core.formatDoubleToString
import com.pixelcreative.saveable.core.getImageFromLabel
import com.pixelcreative.saveable.domain.model.ExpenseDetail
import com.pixelcreative.saveable.ui.theme.Emerald
import com.pixelcreative.saveable.ui.theme.White
import kotlin.math.abs

@Composable
fun ExpenseDetailListView(
    modifier: Modifier = Modifier,
    expenseDetailList: List<ExpenseDetail>?,
    emptyListModifier: Modifier = Modifier
) {
    if (expenseDetailList.isNullOrEmpty()) {
        Text(
            modifier = emptyListModifier,
            text = stringResource(id = R.string.no_expense_text),
            color = White,
            style = MaterialTheme.typography.h3
        )
    } else {
        LazyColumn(modifier = modifier) {
            items(expenseDetailList) { detail ->
                val sign = if (detail.isIncome == true) stringResource(
                    id = R.string.plus
                ) else EMPTY_STRING
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val imagePainter = getImageFromLabel(detail.category ?: "Other")
                    Image(
                        modifier = Modifier.size(44.dp),
                        painter = imagePainter,
                        contentDescription = detail.category.orEmpty()
                    )
                    Text(
                        modifier = Modifier
                            .padding(start = 8.dp),
                        text = detail.category ?: "Other",
                        color = White,
                        style = MaterialTheme.typography.h5
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = stringResource(
                            id = R.string.expense_detail_price_text,
                            sign,
                            abs(detail.price.doubleOrZero()).formatDoubleToString()
                        ),
                        color = if (detail.isIncome == true) {
                            Emerald
                        } else {
                            White
                        },
                        style = MaterialTheme.typography.h5
                    )
                }
            }
        }
    }
}
