package com.pixelcreative.saveable.core

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
fun getCategoryLabel(key: String): String {
    return when (key) {
        "CLOTHES" -> stringResource(R.string.category_clothes)
        "CREDIT_CARD" -> stringResource(R.string.category_credit_card)
        "EDUCATION" -> stringResource(R.string.category_education)
        "ELECTRIC" -> stringResource(R.string.category_electric)
        "ELECTRONIC" -> stringResource(R.string.category_electronic)
        "ENTERTAINMENT" -> stringResource(R.string.category_entertainment)
        "FOOD" -> stringResource(R.string.category_food)
        "FURNITURE" -> stringResource(R.string.category_furniture)
        "HEALTH" -> stringResource(R.string.category_health)
        "KIDS" -> stringResource(R.string.category_kids)
        "OTHER" -> stringResource(R.string.category_other)
        "PET" -> stringResource(R.string.category_pet)
        "PHONE" -> stringResource(R.string.category_phone)
        "RENT" -> stringResource(R.string.category_rent)
        "SHOPPING" -> stringResource(R.string.category_shopping)
        "SUBSCRIPTIONS" -> stringResource(R.string.category_subscriptions)
        "TRANSPORT" -> stringResource(R.string.category_transport)
        "WAGE" -> stringResource(R.string.category_wage)
        "WARMING" -> stringResource(R.string.category_warming)
        "WATER" -> stringResource(R.string.category_water)
        else -> stringResource(R.string.category_other)
    }
}

@Composable
fun getImageFromLabel(label: String): Painter {
    return when (label) {
        "CLOTHES" -> painterResource(id = R.drawable.ic_clothes)
        "CREDIT_CARD" -> painterResource(id = R.drawable.ic_credit_card)
        "EDUCATION" -> painterResource(id = R.drawable.ic_education)
        "ELECTRIC" -> painterResource(id = R.drawable.ic_electric)
        "ELECTRONIC" -> painterResource(id = R.drawable.ic_electronic)
        "ENTERTAINMENT" -> painterResource(id = R.drawable.ic_entertainment)
        "FOOD" -> painterResource(id = R.drawable.ic_food)
        "FURNITURE" -> painterResource(id = R.drawable.ic_furniture)
        "HEALTH" -> painterResource(id = R.drawable.ic_health)
        "KIDS" -> painterResource(id = R.drawable.ic_kids)
        "OTHER" -> painterResource(id = R.drawable.ic_other)
        "PET" -> painterResource(id = R.drawable.ic_pet)
        "PHONE" -> painterResource(id = R.drawable.ic_phone)
        "RENT" -> painterResource(id = R.drawable.ic_rent)
        "SHOPPING" -> painterResource(id = R.drawable.ic_shopping)
        "SUBSCRIPTIONS" -> painterResource(id = R.drawable.ic_subscriptions)
        "TRANSPORT" -> painterResource(id = R.drawable.ic_transport)
        "WAGE" -> painterResource(id = R.drawable.ic_wage)
        "WARMING" -> painterResource(id = R.drawable.ic_warming)
        "WATER" -> painterResource(id = R.drawable.ic_water)
        else -> painterResource(id = R.drawable.ic_other)
    }
}

fun getEnumByLocalizedLabel(label: String, context: Context): CategoriesEnum? {
    val categories = CategoriesEnum.values()
    for (category in categories) {
        val localizedLabel = context.getString(category.labelResId)
        if (localizedLabel == label) {
            return category
        }
    }
    return null
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