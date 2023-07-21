package com.pixelcreative.saveable.ui.theme

import android.app.Activity
import android.view.WindowManager
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = BrightTurquoise,
    primaryVariant = BrightTurquoise,
    secondary = Teal200,
    background = BluishPurple,
    surface = CharlestonGreen,
    onPrimary = White,
    onSecondary = BrightTurquoise,
    onBackground = CharlestonGreen,
    onSurface = Emerald,
)

private val LightColorPalette = lightColors(
    primary = BrightTurquoise,
    primaryVariant = BrightTurquoise,
    secondary = Teal200,
    background = BluishPurple,
    surface = CharlestonGreen,
    onPrimary = White,
    onSecondary = BrightTurquoise,
    onBackground = CharlestonGreen,
    onSurface = Emerald,
)

@Composable
fun SaveableTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {

    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colors.background.toArgb()
           // WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}