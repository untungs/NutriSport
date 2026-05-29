package io.untungs.nutrisport.core.ui.flags

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.PathData
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Flags.Indonesia: ImageVector
    get() {
        if (_Indonesia != null) {
            return _Indonesia!!
        }
        _Indonesia = ImageVector.Builder(
            name = "Indonesia",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            group(
                clipPathData = PathData {
                    moveTo(12f, 0f)
                    lineTo(12f, 0f)
                    arcTo(12f, 12f, 0f, isMoreThanHalf = false, isPositiveArc = true, 24f, 12f)
                    lineTo(24f, 12f)
                    arcTo(12f, 12f, 0f, isMoreThanHalf = false, isPositiveArc = true, 12f, 24f)
                    lineTo(12f, 24f)
                    arcTo(12f, 12f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0f, 12f)
                    lineTo(0f, 12f)
                    arcTo(12f, 12f, 0f, isMoreThanHalf = false, isPositiveArc = true, 12f, 0f)
                    close()
                }
            ) {
                path(fill = SolidColor(Color.White)) {
                    moveTo(12f, 0f)
                    lineTo(12f, 0f)
                    arcTo(12f, 12f, 0f, isMoreThanHalf = false, isPositiveArc = true, 24f, 12f)
                    lineTo(24f, 12f)
                    arcTo(12f, 12f, 0f, isMoreThanHalf = false, isPositiveArc = true, 12f, 24f)
                    lineTo(12f, 24f)
                    arcTo(12f, 12f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0f, 12f)
                    lineTo(0f, 12f)
                    arcTo(12f, 12f, 0f, isMoreThanHalf = false, isPositiveArc = true, 12f, 0f)
                    close()
                }
            }
            group(
                clipPathData = PathData {
                    moveTo(-4f, 0f)
                    horizontalLineToRelative(32f)
                    verticalLineToRelative(24f)
                    horizontalLineToRelative(-32f)
                    close()
                }
            ) {
                path(
                    fill = SolidColor(Color(0xFFF7FCFF)),
                    pathFillType = PathFillType.EvenOdd
                ) {
                    moveTo(-4f, 0f)
                    verticalLineTo(24f)
                    horizontalLineTo(28f)
                    verticalLineTo(0f)
                    horizontalLineTo(-4f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFFE31D1C)),
                    pathFillType = PathFillType.EvenOdd
                ) {
                    moveTo(-4f, 0f)
                    verticalLineTo(12f)
                    horizontalLineTo(28f)
                    verticalLineTo(0f)
                    horizontalLineTo(-4f)
                    close()
                }
            }
        }.build()

        return _Indonesia!!
    }

@Suppress("ObjectPropertyName")
private var _Indonesia: ImageVector? = null
