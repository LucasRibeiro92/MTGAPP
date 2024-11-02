package com.scout.mtgapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Definindo novas cores
val LightGray = Color(0xFFA8ADB4) // #a8adb4
val LightYellow = Color(0xFFFFD966) // #ffd966
val DarkBackground = Color(0xFF434548) // Cor de fundo escura, você pode ajustar
val LightBackground = Color(0xFFFFFFFF) // Branco como fundo claro
val DarkSurface = Color(0xFF434548) // Pode ajustar essa cor também

// Definindo o esquema de cores para o tema escuro
private val DarkColorScheme = darkColorScheme(
    primary = LightYellow,   // Cor principal do tema escuro
    secondary = LightGray,   // Cor secundária do tema escuro
    background = DarkBackground, // Fundo do tema escuro
    surface = DarkSurface,      // Superfície (fundo de cards, por exemplo)
    onPrimary = Color.Black,    // Texto em cima da cor primária
    onSecondary = Color.Black,  // Texto em cima da cor secundária
    onBackground = Color.White,  // Texto sobre o fundo
    onSurface = Color.White      // Texto sobre a superfície
)

// Definindo o esquema de cores para o tema claro
private val LightColorScheme = lightColorScheme(
    primary = LightYellow,   // Cor principal do tema claro
    secondary = LightGray,   // Cor secundária do tema claro
    background = LightBackground, // Fundo do tema claro
    surface = LightBackground, // Superfície (fundo de cards, por exemplo)
    onPrimary = Color.Black,   // Texto em cima da cor primária
    onSecondary = Color.Black, // Texto em cima da cor secundária
    onBackground = DarkBackground, // Texto sobre o fundo
    onSurface = DarkBackground   // Texto sobre a superfície
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
