package com.pixelcreative.saveable.core

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.pixelcreative.saveable.R

enum class CategoriesEnum(val label: String, @DrawableRes val imageResId: Int) {
    CLOTHES("Clothes", R.drawable.ic_clothes),
    CREDIT_CARD("Credit Card", R.drawable.ic_credit_card),
    EDUCATION("Education", R.drawable.ic_education),
    ELECTRIC("Electric", R.drawable.ic_electric),
    ELECTRONIC("Electronic", R.drawable.ic_electronic),
    ENTERTAINMENT("Entertainment", R.drawable.ic_entertainment),
    FOOD("Food", R.drawable.ic_food),
    FURNITURE("Furniture", R.drawable.ic_furniture),
    HEALTH("Health", R.drawable.ic_health),
    KIDS("Kids", R.drawable.ic_kids),
    OTHER("Other", R.drawable.ic_other),
    PET("Pet", R.drawable.ic_pet),
    PHONE("Phone", R.drawable.ic_phone),
    RENT("Rent", R.drawable.ic_rent),
    SHOPPING("Shopping", R.drawable.ic_shopping),
    SUBSCRIPTIONS("Subscriptions", R.drawable.ic_subscriptions),
    TRANSPORT("Transport", R.drawable.ic_transport),
    WAGE("Wage", R.drawable.ic_wage),
    WARMING("Warming", R.drawable.ic_warming),
    WATER("Water", R.drawable.ic_water),
    // Add more options as needed
}

@Composable
fun getImageFromLabel(label: String): Painter? {
    val matchingEnum = CategoriesEnum.values().find { it.label == label }
    return matchingEnum?.let { painterResource(it.imageResId) }
}
fun getAllCategories(): List<String> {
    return CategoriesEnum.values().map { it.label }
}