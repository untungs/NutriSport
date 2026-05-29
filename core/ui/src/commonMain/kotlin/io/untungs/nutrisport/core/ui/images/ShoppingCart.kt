package io.untungs.nutrisport.core.ui.images

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp

fun Images.shoppingCart(outlineColor: Color): ImageVector {
    return ImageVector.Builder(
        name = "ShoppingCartImage",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 24f,
        viewportHeight = 24f
    ).apply {
        path(
            stroke = SolidColor(outlineColor),
            strokeLineWidth = 0.690128f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(7.089f, 15.402f)
            curveTo(7.089f, 15.402f, 6.693f, 15.492f, 6.481f, 15.754f)
            curveTo(6.146f, 16.167f, 6.157f, 16.848f, 7.323f, 16.848f)
            horizontalLineTo(17.108f)
        }
        path(
            stroke = SolidColor(Color(0xFFF24C00)),
            strokeLineWidth = 0.690128f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(8.044f, 16.854f)
            curveTo(8.897f, 16.854f, 9.595f, 17.546f, 9.595f, 18.406f)
            curveTo(9.595f, 19.26f, 8.903f, 19.957f, 8.044f, 19.957f)
            curveTo(7.184f, 19.957f, 6.492f, 19.265f, 6.492f, 18.406f)
            curveTo(6.497f, 17.546f, 7.19f, 16.854f, 8.044f, 16.854f)
            close()
            moveTo(17.208f, 16.854f)
            curveTo(18.062f, 16.854f, 18.76f, 17.546f, 18.76f, 18.406f)
            curveTo(18.76f, 19.26f, 18.068f, 19.957f, 17.208f, 19.957f)
            curveTo(16.354f, 19.957f, 15.656f, 19.265f, 15.656f, 18.406f)
            curveTo(15.662f, 17.546f, 16.354f, 16.854f, 17.208f, 16.854f)
            close()
        }
        path(
            stroke = SolidColor(outlineColor),
            strokeLineWidth = 0.690128f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(2.339f, 4.045f)
            horizontalLineTo(3.757f)
            curveTo(4.538f, 4.045f, 5.331f, 4.96f, 5.521f, 6.082f)
            lineTo(7.089f, 15.403f)
            moveTo(7.089f, 15.403f)
            curveTo(7.273f, 15.313f, 8.083f, 15.325f, 9.216f, 15.196f)
            lineTo(17.348f, 14.27f)
            curveTo(18.481f, 14.141f, 19.658f, 13.148f, 19.977f, 12.054f)
            lineTo(21.026f, 8.443f)
            curveTo(21.344f, 7.349f, 21.623f, 6.383f, 21.651f, 6.288f)
            curveTo(21.679f, 6.193f, 21.645f, 6.115f, 21.573f, 6.115f)
            horizontalLineTo(19.379f)
            lineTo(5.526f, 6.11f)
            moveTo(7.089f, 15.403f)
            curveTo(7.089f, 15.403f, 6.693f, 15.492f, 6.481f, 15.754f)
        }
    }.build()
}

@Composable
fun rememberShoppingCartIcon(outlineColor: Color = MaterialTheme.colorScheme.onSurface): ImageVector {
    return remember(outlineColor) {
        Images.shoppingCart(outlineColor)
    }
}

@Composable
fun rememberShoppingCartPainter(outlineColor: Color = MaterialTheme.colorScheme.onSurface): Painter {
    return rememberVectorPainter(rememberShoppingCartIcon(outlineColor))
}
