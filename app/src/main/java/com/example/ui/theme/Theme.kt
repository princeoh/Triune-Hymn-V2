package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = RoyalBluePrimary,
    onPrimary = PureWhite,
    primaryContainer = RoyalBlueContainer,
    onPrimaryContainer = RoyalBlueDark,
    secondary = SacredGold,
    onSecondary = TextDark,
    secondaryContainer = GoldContainer,
    onSecondaryContainer = SacredGoldDark,
    tertiary = RoyalBlueLight,
    onTertiary = PureWhite,
    background = CreamWhite,
    onBackground = TextDark,
    surface = PureWhite,
    onSurface = TextDark,
    surfaceVariant = RoyalBlueSurface,
    onSurfaceVariant = TextMuted,
    outline = CardBorder
)

private val DarkColorScheme = darkColorScheme(
    primary = RoyalBlueAccent,
    onPrimary = PureWhite,
    primaryContainer = SanctuaryDarkCard,
    onPrimaryContainer = RoyalBlueContainer,
    secondary = SacredGoldLight,
    onSecondary = PureWhite,
    secondaryContainer = SanctuaryDarkCard,
    onSecondaryContainer = SacredGoldLight,
    tertiary = SacredGold,
    onTertiary = TextDark,
    background = SanctuaryDarkBackground,
    onBackground = TextLight,
    surface = SanctuaryDarkSurface,
    onSurface = TextLight,
    surfaceVariant = SanctuaryDarkCard,
    onSurfaceVariant = TextMutedDark,
    outline = SanctuaryDarkBorder
)

@Composable
fun TriuneHymnTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
