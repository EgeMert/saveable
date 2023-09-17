package com.pixelcreative.saveable.core

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.pixelcreative.saveable.R

enum class CategoriesEnum(@StringRes val labelResId: Int, @DrawableRes val imageResId: Int) {
    CLOTHES(R.string.category_clothes, R.drawable.ic_clothes),
    CREDIT_CARD(R.string.category_credit_card, R.drawable.ic_credit_card),
    EDUCATION(R.string.category_education, R.drawable.ic_education),
    ELECTRIC(R.string.category_electric, R.drawable.ic_electric),
    ELECTRONIC(R.string.category_electronic, R.drawable.ic_electronic),
    ENTERTAINMENT(R.string.category_entertainment, R.drawable.ic_entertainment),
    FOOD(R.string.category_food, R.drawable.ic_food),
    FURNITURE(R.string.category_furniture, R.drawable.ic_furniture),
    HEALTH(R.string.category_health, R.drawable.ic_health),
    KIDS(R.string.category_kids, R.drawable.ic_kids),
    PET(R.string.category_pet, R.drawable.ic_pet),
    PHONE(R.string.category_phone, R.drawable.ic_phone),
    RENT(R.string.category_rent, R.drawable.ic_rent),
    SHOPPING(R.string.category_shopping, R.drawable.ic_shopping),
    SUBSCRIPTIONS(R.string.category_subscriptions, R.drawable.ic_subscriptions),
    TRANSPORT(R.string.category_transport, R.drawable.ic_transport),
    WAGE(R.string.category_wage, R.drawable.ic_wage),
    WARMING(R.string.category_warming, R.drawable.ic_warming),
    WATER(R.string.category_water, R.drawable.ic_water),
    OTHER(R.string.category_other, R.drawable.ic_other);
}

@Composable
fun getImageFromLabel(label: String): Painter {
    return when (label) {
        "Clothes" -> painterResource(id = R.drawable.ic_clothes)
        "Credit Card" -> painterResource(id = R.drawable.ic_credit_card)
        "Education" -> painterResource(id = R.drawable.ic_education)
        "Electric" -> painterResource(id = R.drawable.ic_electric)
        "Electronic" -> painterResource(id = R.drawable.ic_electronic)
        "Entertainment" -> painterResource(id = R.drawable.ic_entertainment)
        "Food" -> painterResource(id = R.drawable.ic_food)
        "Furniture" -> painterResource(id = R.drawable.ic_furniture)
        "Health" -> painterResource(id = R.drawable.ic_health)
        "Kids" -> painterResource(id = R.drawable.ic_kids)
        "Other" -> painterResource(id = R.drawable.ic_other)
        "Pet" -> painterResource(id = R.drawable.ic_pet)
        "Phone" -> painterResource(id = R.drawable.ic_phone)
        "Rent" -> painterResource(id = R.drawable.ic_rent)
        "Shopping" -> painterResource(id = R.drawable.ic_shopping)
        "Subscriptions" -> painterResource(id = R.drawable.ic_subscriptions)
        "Transport" -> painterResource(id = R.drawable.ic_transport)
        "Wage" -> painterResource(id = R.drawable.ic_wage)
        "Warming" -> painterResource(id = R.drawable.ic_warming)
        "Water" -> painterResource(id = R.drawable.ic_water)
        else -> painterResource(id = R.drawable.ic_food)
    }
}

fun getAllCategories(): List<CategoriesEnum> {
    return listOf(
        CategoriesEnum.CLOTHES,
        CategoriesEnum.CREDIT_CARD,
        CategoriesEnum.EDUCATION,
        CategoriesEnum.ELECTRIC,
        CategoriesEnum.ELECTRONIC,
        CategoriesEnum.ENTERTAINMENT,
        CategoriesEnum.FOOD,
        CategoriesEnum.FURNITURE,
        CategoriesEnum.HEALTH,
        CategoriesEnum.KIDS,
        CategoriesEnum.PET,
        CategoriesEnum.PHONE,
        CategoriesEnum.RENT,
        CategoriesEnum.SHOPPING,
        CategoriesEnum.SUBSCRIPTIONS,
        CategoriesEnum.TRANSPORT,
        CategoriesEnum.WAGE,
        CategoriesEnum.WARMING,
        CategoriesEnum.WATER,
        CategoriesEnum.OTHER
    )
}