package com.pixelcreative.saveable.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pixelcreative.saveable.core.getImageFromLabel
import com.pixelcreative.saveable.domain.model.ExpenseDetail
import com.pixelcreative.saveable.ui.theme.Emerald
import com.pixelcreative.saveable.ui.theme.White

@Composable
fun ExpenseDetailListView(
    modifier: Modifier = Modifier,
    expenseDetailList: List<ExpenseDetail>?
) {
    expenseDetailList?.let { expenseDetail ->
        Column(modifier = modifier) {
            expenseDetail.forEach { detail ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val imagePainter = getImageFromLabel(detail.category ?: "Food")
                    imagePainter?.let {
                        Image(
                            modifier = Modifier.size(44.dp),
                            painter = it,
                            contentDescription = detail.category.orEmpty()
                        )
                    }
                    Text(
                        modifier = Modifier
                            .padding(start = 8.dp),
                        text = detail.category.orEmpty(),
                        color = White,
                        style = MaterialTheme.typography.h5
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = if (detail.isIncome == true) {
                            "+ $ " + detail.price.toString()
                        } else {
                            "$ " + detail.price.toString()
                        },
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
    } ?: run {
        Text(
            modifier = Modifier
                .padding(8.dp),
            text = "Hiç harcamanız yok, hadi başlayalım \uD83D\uDC7B",
            color = White,
            style = MaterialTheme.typography.h3
        )
    }
}