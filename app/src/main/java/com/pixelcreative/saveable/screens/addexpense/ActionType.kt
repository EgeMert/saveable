package com.pixelcreative.saveable.screens.addexpense

import androidx.compose.ui.graphics.Color
import com.pixelcreative.saveable.ui.theme.*

sealed class ActionType(val symbol: String, val buttonColor: Color) {
data class Number(val number: Int) : ActionType(number.toString(), ButtonBlue)
    data class Operator(val operation: Operators) : ActionType(operation.symbol, ButtonPink)

    object Calculate : ActionType("=", ButtonYellow)
    object Delete : ActionType("⬅\uFE0F", ButtonBlue)
    object Clear : ActionType("AC", ButtonPink)
    object Calendar : ActionType("\uD83D\uDCC5", ButtonPink)
    object Decimal : ActionType(".", ButtonBlue)
    object Percentage: ActionType("%", ButtonPink)
}

sealed class Operators(val symbol: String) {
    object Add : Operators("+")
    object Subtract : Operators("-")
    object Multiply : Operators("x")
    object Divide : Operators("÷")
    object Power : Operators("^")
}