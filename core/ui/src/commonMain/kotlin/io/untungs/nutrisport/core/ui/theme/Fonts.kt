package io.untungs.nutrisport.core.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import nutrisport.core.ui.generated.resources.Res
import nutrisport.core.ui.generated.resources.bebas_neue_regular
import nutrisport.core.ui.generated.resources.roboto_condensed_medium
import nutrisport.core.ui.generated.resources.roboto_regular
import org.jetbrains.compose.resources.Font

@Composable
fun BebasNeueFont() = FontFamily(Font(Res.font.bebas_neue_regular))

@Composable
fun RobotoFont() = FontFamily(Font(Res.font.roboto_regular))

@Composable
fun RobotoCondensedFont() = FontFamily(
    Font(Res.font.roboto_condensed_medium, weight = FontWeight.Medium)
)

object FontSize {
    val EXTRA_SMALL = 10.sp
    val SMALL = 12.sp
    val REGULAR = 14.sp
    val EXTRA_REGULAR = 16.sp
    val MEDIUM = 18.sp
    val EXTRA_MEDIUM = 20.sp
    val LARGE = 30.sp
    val EXTRA_LARGE = 40.sp
}

@Composable
fun Typography() = Typography(
    displayLarge = TextStyle(
        fontFamily = BebasNeueFont(),
        fontSize = FontSize.EXTRA_LARGE
    ),
    displayMedium = TextStyle(
        fontFamily = BebasNeueFont(),
        fontSize = FontSize.LARGE
    ),
    titleLarge = TextStyle(
        fontFamily = RobotoCondensedFont(),
        fontSize = FontSize.EXTRA_MEDIUM,
        fontWeight = FontWeight.Medium
    ),
    titleMedium = TextStyle(
        fontFamily = BebasNeueFont(),
        fontSize = FontSize.EXTRA_MEDIUM,
    ),
    bodyLarge = TextStyle(
        fontFamily = RobotoFont(),
        fontSize = FontSize.EXTRA_REGULAR
    ),
    bodyMedium = TextStyle(
        fontFamily = RobotoFont(),
        fontSize = FontSize.REGULAR
    ),
    bodySmall = TextStyle(
        fontFamily = RobotoFont(),
        fontSize = FontSize.SMALL
    ),
    labelLarge = TextStyle(
        fontFamily = RobotoFont(),
        fontSize = FontSize.EXTRA_SMALL
    ),
)