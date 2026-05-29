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

fun Images.cat(outlineColor: Color): ImageVector {
    return ImageVector.Builder(
        name = "Cat",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 24f,
        viewportHeight = 24f
    ).apply {
        // Outline Paths
        path(
            stroke = SolidColor(outlineColor),
            strokeLineWidth = 0.5f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(5.967f, 5.762f)
            curveTo(5.872f, 5.522f, 4.867f, 3.038f, 5.509f, 2.625f)
            curveTo(5.978f, 2.323f, 7.105f, 3.289f, 7.741f, 3.892f)
            moveTo(10.739f, 3.892f)
            curveTo(11.375f, 3.289f, 12.502f, 2.323f, 12.971f, 2.625f)
            curveTo(13.608f, 3.038f, 12.608f, 5.522f, 12.514f, 5.762f)
        }
        path(
            stroke = SolidColor(outlineColor),
            strokeLineWidth = 0.5f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(12.084f, 20.302f)
            horizontalLineTo(12.101f)
            curveTo(16.108f, 20.302f, 19.088f, 17.729f, 19.351f, 14.581f)
            curveTo(19.457f, 13.32f, 19.334f, 12.025f, 18.553f, 11.043f)
            curveTo(18.341f, 10.769f, 17.961f, 10.691f, 17.654f, 10.853f)
            curveTo(17.409f, 10.981f, 17.269f, 11.232f, 17.269f, 11.489f)
            curveTo(17.269f, 11.606f, 17.297f, 11.724f, 17.358f, 11.83f)
            curveTo(17.615f, 12.298f, 17.805f, 12.756f, 17.805f, 13.337f)
            curveTo(17.805f, 15.642f, 16.861f, 17.629f, 14.372f, 18.533f)
            curveTo(14.372f, 18.533f, 14.277f, 18.566f, 14.155f, 18.605f)
            moveTo(10.248f, 20.302f)
            horizontalLineTo(8.757f)
            moveTo(5.955f, 5.93f)
            curveTo(5.955f, 7.61f, 7.423f, 9.536f, 9.237f, 9.536f)
            curveTo(11.051f, 9.536f, 12.519f, 7.61f, 12.519f, 5.93f)
            curveTo(12.525f, 4.708f, 11.051f, 3.63f, 9.237f, 3.63f)
            curveTo(7.423f, 3.63f, 5.955f, 4.708f, 5.955f, 5.93f)
            close()
        }
        path(
            stroke = SolidColor(outlineColor),
            strokeLineWidth = 0.5f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(11.453f, 8.479f)
            lineTo(13.82f, 13.686f)
            curveTo(16.415f, 18.933f, 12.084f, 20.295f, 12.084f, 20.295f)
            moveTo(7.055f, 8.507f)
            curveTo(7.055f, 8.507f, 7.356f, 9.975f, 6.826f, 11.476f)
            curveTo(6.463f, 12.492f, 5.905f, 13.725f, 6.073f, 14.948f)
            curveTo(6.435f, 17.565f, 7.016f, 18.062f, 6.899f, 20.295f)
        }
        // Whiskers (Orange)
        path(
            stroke = SolidColor(Color(0xFFF24C00)),
            strokeLineWidth = 0.5f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(7.68f, 6.799f)
            curveTo(6.29f, 6.297f, 4.633f, 6.649f, 4.633f, 6.649f)
            moveTo(7.68f, 7.798f)
            curveTo(6.865f, 7.687f, 6.006f, 8.066f, 6.006f, 8.066f)
            moveTo(10.856f, 6.799f)
            curveTo(12.246f, 6.303f, 13.903f, 6.649f, 13.903f, 6.649f)
            moveTo(10.856f, 7.798f)
            curveTo(11.671f, 7.687f, 12.53f, 8.066f, 12.53f, 8.066f)
        }
        // Final Outline Path
        path(
            stroke = SolidColor(outlineColor),
            strokeLineWidth = 0.5f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(12.419f, 17.193f)
            curveTo(12.162f, 18.12f, 12.095f, 19.593f, 12.084f, 20.537f)
            curveTo(12.078f, 21.039f, 11.671f, 21.435f, 11.168f, 21.435f)
            curveTo(10.672f, 21.435f, 10.27f, 21.039f, 10.253f, 20.548f)
            curveTo(10.208f, 19.069f, 10.242f, 16.15f, 11.107f, 15.083f)
            moveTo(7.892f, 15.067f)
            curveTo(8.769f, 16.155f, 8.802f, 19.041f, 8.752f, 20.514f)
            curveTo(8.735f, 21.017f, 8.328f, 21.413f, 7.825f, 21.413f)
            curveTo(7.317f, 21.413f, 6.904f, 21.005f, 6.899f, 20.497f)
            verticalLineTo(20.302f)
            moveTo(9.07f, 7.035f)
            horizontalLineTo(9.405f)
        }
    }.build()
}

@Composable
fun rememberCatIcon(outlineColor: Color = MaterialTheme.colorScheme.onSurface): ImageVector {
    return remember(outlineColor) {
        Images.cat(outlineColor)
    }
}

@Composable
fun rememberCatPainter(outlineColor: Color = MaterialTheme.colorScheme.onSurface): Painter {
    return rememberVectorPainter(rememberCatIcon(outlineColor))
}
