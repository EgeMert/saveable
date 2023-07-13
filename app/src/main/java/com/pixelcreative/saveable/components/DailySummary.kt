package com.pixelcreative.saveable.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.pixelcreative.saveable.domain.model.Expense
import com.pixelcreative.saveable.ui.theme.BrightTurquoise
import com.pixelcreative.saveable.ui.theme.CharlestonGreen
import com.pixelcreative.saveable.ui.theme.PhilippineGray
import com.pixelcreative.saveable.ui.theme.White

@Composable
fun DailySummary(
    modifier: Modifier = Modifier,
    dailyExpense: Expense?
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(CharlestonGreen)
    ) {
        dailyExpense?.let { expense ->
            Text(
                modifier = Modifier.padding(
                    top = 8.dp,
                    start = 8.dp,
                    end = 8.dp
                ),
                text = "Bugün tam olarak",
                color = PhilippineGray,
                style = MaterialTheme.typography.h6
            )
            Row(
                modifier = Modifier.padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = expense.dailyTotalExpense,
                    color = White,
                    style = MaterialTheme.typography.h3
                )
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = "harcadınız",
                    color = PhilippineGray,
                    style = MaterialTheme.typography.h6
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(end = 4.dp)
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = "Detaylar",
                    color = BrightTurquoise,
                    style = MaterialTheme.typography.h6
                )
                Image(
                    modifier = Modifier
                        .size(20.dp),
                    painter = rememberAsyncImagePainter(
                        model = "https://cdn1.iconfinder.com/data/icons/DarkGlass_Reworked/128x128/actions/next.png"
                    ),
                    contentDescription = null
                )
            }

        } ?: run {
            Text(
                modifier = Modifier
                    .padding(8.dp),
                text = "Bugun için hiç harcamanız yok \uD83E\uDD11",
                color = White,
                style = MaterialTheme.typography.h3
            )
        }
    }
}