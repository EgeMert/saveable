package com.pixelcreative.saveable.core

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.pixelcreative.saveable.R

enum class CategoriesEnum(val label: String, @DrawableRes val imageResId: Int) {
    FOOD("Food", R.drawable.ic_food),
    SHOPPING("Shopping", R.drawable.ic_shopping),
    TRANSPORT("Transport", R.drawable.ic_transport),
    HEALTH("Health", R.drawable.ic_health)
    // Add more options as needed
}

@Composable
fun getImageFromLabel(label: String): Painter? {
    val matchingEnum = CategoriesEnum.values().find { it.label == label }
    return matchingEnum?.let { painterResource(it.imageResId) }
}