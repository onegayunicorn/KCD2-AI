package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme =
  darkColorScheme(
    primary = Gold,
    secondary = GreenAccent,
    background = BackgroundDark,
    surface = BackgroundDark,
    onPrimary = BackgroundDark,
    onSecondary = BackgroundDark,
    onBackground = TextOffWhite,
    onSurface = TextOffWhite,
  )

@Composable
fun MyApplicationTheme(
  darkTheme: Boolean = true, // Force dark theme for this design
  content: @Composable () -> Unit,
) {
  MaterialTheme(colorScheme = DarkColorScheme, typography = Typography, content = content)
}
