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
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.pixelcreative.saveable.domain.model.Expense
import com.pixelcreative.saveable.domain.model.ExpenseDetail
import com.pixelcreative.saveable.domain.model.ExpenseDetailList
import com.pixelcreative.saveable.ui.theme.CharlestonGreen
import com.pixelcreative.saveable.ui.theme.Emerald
import com.pixelcreative.saveable.ui.theme.PhilippineGray
import com.pixelcreative.saveable.ui.theme.White
import java.time.LocalDate

@Preview
@Composable
fun SummaryCard_Preview() {
    SummaryCard(
        latestExpense = Expense(
            id = 0L,
            date = "Date",
            expenseDetailList = ExpenseDetailList(
                expenseDetail = listOf(
                    ExpenseDetail(
                        id = LocalDate.now().toString(),
                        price = 350.0,
                        isIncome = false,
                        category = "Alışveriş"
                    ),
                    ExpenseDetail(
                        id = LocalDate.now().toString(),
                        price = 200.0,
                        isIncome = false,
                        category = "Giyim"
                    )
                )
            ),
            dailyTotalExpense = "550.0 TL"
        )
    )
}

@Preview
@Composable
fun SummaryCard_Preview2() {
    SummaryCard(
        latestExpense = null
    )
}

@Composable
fun SummaryCard(
    modifier: Modifier = Modifier,
    latestExpense: Expense?
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(CharlestonGreen)
    ) {
        latestExpense?.let { latestExpense ->
            latestExpense.expenseDetailList?.expenseDetail?.forEachIndexed { index, expenseDetail ->

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier
                            .size(32.dp),
                        painter = rememberAsyncImagePainter(
                            model = "https://cdn1.iconfinder.com/data/icons/unicons-line-vol-5/24/shopping-cart-256.png"
                        ),
                        contentDescription = null
                    )
                    Text(
                        modifier = Modifier
                            .padding(start = 8.dp),
                        text = expenseDetail.category.orEmpty(),
                        color = White,
                        style = MaterialTheme.typography.subtitle1
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = expenseDetail.price.toString() + " TL",
                        color = Emerald,
                        style = MaterialTheme.typography.subtitle1
                    )
                }

                if (index != latestExpense.expenseDetailList?.expenseDetail?.size?.minus(1)) {
                    Divider(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                        color = PhilippineGray,
                        thickness = 1.dp
                    )
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
}
