package com.lookmyup.app.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.lookmyup.app.R

private val FashionFont = FontFamily(
    Font(R.font.playfair_display, FontWeight.Normal),
    Font(R.font.playfair_display_bold, FontWeight.Bold),
    Font(R.font.playfair_display_black, FontWeight.Black)
)

private val ModernFont = FontFamily(
    Font(R.font.montserrat, FontWeight.Normal),
    Font(R.font.montserrat_bold, FontWeight.Bold),
    Font(R.font.montserrat_light, FontWeight.Light)
)

val FashionTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = FashionFont,
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp,
        letterSpacing = (-1).sp
    ),
    displayMedium = TextStyle(
        fontFamily = FashionFont,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp
    ),
    displaySmall = TextStyle(
        fontFamily = FashionFont,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = FashionFont,
        fontWeight = FontWeight.Bold,
        fontSize = 26.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = FashionFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = FashionFont,
        fontWeight = FontWeight.Medium,
        fontSize = 22.sp
    ),
    titleLarge = TextStyle(
        fontFamily = FashionFont,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    titleMedium = TextStyle(
        fontFamily = ModernFont,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp
    ),
    titleSmall = TextStyle(
        fontFamily = ModernFont,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = ModernFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = ModernFont,
        fontWeight = FontWeight.Light,
        fontSize = 14.sp
    ),
    bodySmall = TextStyle(
        fontFamily = ModernFont,
        fontWeight = FontWeight.Light,
        fontSize = 12.sp
    ),
    labelLarge = TextStyle(
        fontFamily = ModernFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp
    ),
    labelMedium = TextStyle(
        fontFamily = ModernFont,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp
    ),
    labelSmall = TextStyle(
        fontFamily = ModernFont,
        fontWeight = FontWeight.Medium,
        fontSize = 10.sp
    )
)