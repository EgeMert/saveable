package com.pixelcreative.saveable.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.pixelcreative.saveable.R

val Rubic = FontFamily(
    Font(R.font.rubic_regular, FontWeight.Normal),
    Font(R.font.rubic_medium, FontWeight.Medium),
    Font(R.font.rubic_light, FontWeight.Light),
    Font(R.font.rubic_bold, FontWeight.Bold),
    Font(R.font.rubic_black, FontWeight.Black),
    Font(R.font.rubic_extra_bold, FontWeight.ExtraBold),
    Font(R.font.rubic_semi_bold, FontWeight.SemiBold)
)
val Typography = Typography(
    h4 = TextStyle(
        fontFamily = Rubic,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
    ),
    h5 = TextStyle(
        fontFamily = Rubic,
        fontWeight = FontWeight.Normal,
        fontSize = 17.sp,
        lineHeight = 22.sp
    ),
    h6 = TextStyle(
        fontFamily = Rubic,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        lineHeight = 25.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = Rubic,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        lineHeight = 25.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = Rubic,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 25.sp
    ),
    h3 = TextStyle(
        fontFamily = Rubic,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        lineHeight = 20.sp
    ),
    h2 = TextStyle(
        fontFamily = Rubic,
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp,
        lineHeight = 34.sp
    ),
    h1 = TextStyle(
        fontFamily = Rubic,
        fontWeight = FontWeight.Bold,
        fontSize = 34.sp,
        lineHeight = 41.sp

    )
)