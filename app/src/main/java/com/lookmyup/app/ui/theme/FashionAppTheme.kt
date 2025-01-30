package com.lookmyup.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

@Composable
fun FashionAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        darkColorScheme(
            primary = LightGray,
            onPrimary = DarkPrimary,
            secondary = GoldAccent,
            background = BackgroundDark,
            onBackground = LightPrimary,
            surface = DarkGray,
            onSurface = LightPrimary
        )
    } else {
        lightColorScheme(
            primary = DarkPrimary,
            onPrimary = LightPrimary,
            secondary = AccentColor,
            background = BackgroundLight,
            onBackground = DarkPrimary,
            surface = LightGray,
            onSurface = DarkPrimary
        )
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = FashionTypography,
        shapes = FashionShapes,
        content = content
    )
}