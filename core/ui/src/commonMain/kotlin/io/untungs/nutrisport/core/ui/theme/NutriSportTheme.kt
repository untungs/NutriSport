package io.untungs.nutrisport.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

val lightScheme = lightColorScheme(
    primary = Yellowish,
    onPrimary = Black,
    primaryContainer = Yellowish,
    onPrimaryContainer = Black,
    secondary = Orange,
    background = White,
    surface = White,
    onSurface = Black,
    errorContainer = Red,
    onErrorContainer = White,
    outline = GrayDarker,
    surfaceBright = GrayLighter,
    surfaceDim = Gray,
)

val darkScheme = darkColorScheme(
    primary = Yellowish,
    onPrimary = Black,
    primaryContainer = Yellowish,
    onPrimaryContainer = Black,
    secondary = Orange,
    background = DarkBlack,
    surface = DarkBlack,
    onSurface = White,
    errorContainer = Red,
    onErrorContainer = White,
    outline = DarkDarker,
    surfaceBright = DarkLighter,
    surfaceDim = DarkBlack,
)

@Composable
fun NutriSportTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> darkScheme
        else -> lightScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography(),
        content = content
    )
}

