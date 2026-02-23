package io.untungs.nutrisport.shared.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

val lightScheme = lightColorScheme(
    primary = Yellowish,
    secondary = Orange,
    surface = White,
    onSurface = TextPrimary,
    surfaceBright = GrayLighter,
    outline = GrayDarker
)

val darkScheme = darkColorScheme(
    primary = Yellowish,
    secondary = Orange,
    surface = DarkBlack,
    onSurface = White,
    surfaceBright = DarkLighter,
    outline = DarkDarker
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

