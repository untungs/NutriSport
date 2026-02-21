package io.untungs.nutrisport.shared.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.PathData
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icon.Weight: ImageVector
    get() {
        if (_Weight != null) {
            return _Weight!!
        }
        _Weight = ImageVector.Builder(
            name = "Weight",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            group(
                clipPathData = PathData {
                    moveTo(0f, 0f)
                    horizontalLineToRelative(24f)
                    verticalLineToRelative(24f)
                    horizontalLineToRelative(-24f)
                    close()
                }
            ) {
                path(fill = SolidColor(Color(0xFF1C1B1F))) {
                    moveTo(6.356f, 19f)
                    horizontalLineTo(17.644f)
                    curveTo(17.734f, 19f, 17.811f, 18.963f, 17.875f, 18.889f)
                    curveTo(17.939f, 18.816f, 17.965f, 18.734f, 17.952f, 18.644f)
                    lineTo(16.614f, 9.26f)
                    curveTo(16.601f, 9.183f, 16.565f, 9.12f, 16.508f, 9.072f)
                    curveTo(16.45f, 9.024f, 16.383f, 9f, 16.306f, 9f)
                    horizontalLineTo(7.694f)
                    curveTo(7.617f, 9f, 7.55f, 9.024f, 7.492f, 9.072f)
                    curveTo(7.435f, 9.12f, 7.399f, 9.183f, 7.386f, 9.26f)
                    lineTo(6.048f, 18.644f)
                    curveTo(6.035f, 18.734f, 6.061f, 18.816f, 6.125f, 18.889f)
                    curveTo(6.189f, 18.963f, 6.266f, 19f, 6.356f, 19f)
                    close()
                    moveTo(12f, 7.5f)
                    curveTo(12.283f, 7.5f, 12.521f, 7.404f, 12.712f, 7.213f)
                    curveTo(12.904f, 7.021f, 13f, 6.783f, 13f, 6.5f)
                    curveTo(13f, 6.217f, 12.904f, 5.979f, 12.712f, 5.787f)
                    curveTo(12.521f, 5.596f, 12.283f, 5.5f, 12f, 5.5f)
                    curveTo(11.717f, 5.5f, 11.479f, 5.596f, 11.288f, 5.787f)
                    curveTo(11.096f, 5.979f, 11f, 6.217f, 11f, 6.5f)
                    curveTo(11f, 6.783f, 11.096f, 7.021f, 11.288f, 7.213f)
                    curveTo(11.479f, 7.404f, 11.717f, 7.5f, 12f, 7.5f)
                    close()
                    moveTo(14.267f, 7.5f)
                    horizontalLineTo(16.315f)
                    curveTo(16.77f, 7.5f, 17.164f, 7.649f, 17.495f, 7.947f)
                    curveTo(17.827f, 8.245f, 18.028f, 8.617f, 18.098f, 9.061f)
                    lineTo(19.437f, 18.446f)
                    curveTo(19.514f, 18.988f, 19.374f, 19.466f, 19.017f, 19.88f)
                    curveTo(18.661f, 20.293f, 18.203f, 20.5f, 17.644f, 20.5f)
                    horizontalLineTo(6.356f)
                    curveTo(5.797f, 20.5f, 5.339f, 20.293f, 4.983f, 19.88f)
                    curveTo(4.626f, 19.466f, 4.486f, 18.988f, 4.563f, 18.446f)
                    lineTo(5.902f, 9.061f)
                    curveTo(5.972f, 8.617f, 6.173f, 8.245f, 6.505f, 7.947f)
                    curveTo(6.836f, 7.649f, 7.23f, 7.5f, 7.685f, 7.5f)
                    horizontalLineTo(9.733f)
                    curveTo(9.664f, 7.346f, 9.607f, 7.187f, 9.564f, 7.022f)
                    curveTo(9.521f, 6.857f, 9.5f, 6.683f, 9.5f, 6.5f)
                    curveTo(9.5f, 5.801f, 9.742f, 5.21f, 10.226f, 4.726f)
                    curveTo(10.71f, 4.242f, 11.301f, 4f, 12f, 4f)
                    curveTo(12.699f, 4f, 13.29f, 4.242f, 13.774f, 4.726f)
                    curveTo(14.258f, 5.21f, 14.5f, 5.801f, 14.5f, 6.5f)
                    curveTo(14.5f, 6.683f, 14.479f, 6.857f, 14.436f, 7.022f)
                    curveTo(14.392f, 7.187f, 14.336f, 7.346f, 14.267f, 7.5f)
                    close()
                }
            }
        }.build()

        return _Weight!!
    }

@Suppress("ObjectPropertyName")
private var _Weight: ImageVector? = null
