package com.pixelcreative.saveable.core

import java.time.LocalDate

fun Double?.doubleOrZero(): Double {
    return this ?: 0.0
}

fun getLocalDateAsString(): String{
    return (LocalDate.now().dayOfMonth + LocalDate.now().monthValue + LocalDate.now().year).toString()
}

fun String?.orNone(): String {
    return this ?: "-"
}