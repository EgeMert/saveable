package com.pixelcreative.saveable.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pixelcreative.saveable.R
import com.pixelcreative.saveable.core.orNone
import com.pixelcreative.saveable.ui.theme.BluishPurple
import com.pixelcreative.saveable.ui.theme.Pinball
import com.pixelcreative.saveable.ui.theme.White
import com.pixelcreative.saveable.ui.theme.ZimaBlue

@Preview
@Composable
fun TotalBalanceCardNull_Preview() {
    TotalBalanceCard(
        modifier = Modifier.defaultMinSize(160.dp),
        colors = listOf(ZimaBlue, BluishPurple)
    )
}

@Preview
@Composable
fun TotalBalanceCard_Preview() {
    TotalBalanceCard(
        modifier = Modifier.defaultMinSize(160.dp),
        colors = listOf(ZimaBlue, BluishPurple),
        totalBalance = "$ 23,970.30",
        monthlyIncome = "$ 5,235.25",
        monthlyExpense = "$ 3,710.80"
    )
}

@Composable
fun TotalBalanceCard(
    modifier: Modifier = Modifier,
    colors: List<Color>,
    totalBalance: String? = null,
    monthlyIncome: String? = null,
    monthlyExpense: String? = null,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(brush = Brush.horizontalGradient(colors = colors))
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {

            Text(
                text = stringResource(id = R.string.total_balance_title),
                color = Pinball,
                style = MaterialTheme.typography.h5
            )

            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = totalBalance.orNone(),
                color = White,
                style = MaterialTheme.typography.h3
            )

            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = stringResource(id = R.string.this_month_summary),
                color = Pinball,
                style = MaterialTheme.typography.h5
            )

            Row(
                modifier = Modifier.padding(top = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_income),
                    contentDescription = null
                )

                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = monthlyIncome.orNone(),
                    color = White,
                    style = MaterialTheme.typography.h3
                )
            }

            Row(
                modifier = Modifier.padding(top = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_expense),
                    contentDescription = null
                )

                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = monthlyExpense.orNone(),
                    color = White,
                    style = MaterialTheme.typography.h3
                )
            }
        }
    }
}
