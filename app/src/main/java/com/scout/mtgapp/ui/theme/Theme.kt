package com.scout.mtgapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Definindo as novas cores
val DeepRed = Color(0xFF660b15)
val BrightRed = Color(0xFFab1323)
val PureWhite = Color(0xFFFFFFFF)
val LightGray = Color(0xFFE1E1E1)
val DarkGray = Color(0xFF1C1C1C)

val MyPurple = Color(0xff8c51a5)
val MyBlue = Color(0xff2c2d61)

private val DarkColorScheme = darkColorScheme(
    primary = BrightRed,
    secondary = DeepRed,
    background = DarkGray,
    surface = DarkGray,
    onPrimary = PureWhite,
    onSecondary = PureWhite,
    onBackground = PureWhite,
    onSurface = LightGray
)

private val LightColorScheme = lightColorScheme(
    primary = BrightRed,
    secondary = DeepRed,
    background = PureWhite,
    surface = LightGray,
    onPrimary = PureWhite,
    onSecondary = PureWhite,
    onBackground = DarkGray,
    onSurface = DarkGray
)

@Composable
fun MTGAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}
