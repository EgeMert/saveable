package com.pixelcreative.saveable.core

import java.text.DecimalFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter


fun Double?.doubleOrZero(): Double {
    return this ?: 0.0
}

fun getLocalDateAsString(): String {
    val currentDate = LocalDate.now()
    val dateFormatter = DateTimeFormatter.ofPattern("d/MM/uuuu")
    return currentDate.format(dateFormatter)
}

fun String?.orNone(): String {
    return this ?: "-"
}

fun Double?.formatDoubleToString(): String {
    val decimalFormat = DecimalFormat("#.##")
    return decimalFormat.format(this ?: 0.0)
}