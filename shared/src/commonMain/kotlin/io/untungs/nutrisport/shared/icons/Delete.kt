package io.untungs.nutrisport.shared.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icon.Delete: ImageVector
    get() {
        if (_Delete != null) {
            return _Delete!!
        }
        _Delete = ImageVector.Builder(
            name = "Delete",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color.Black),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(3f, 6f)
                horizontalLineTo(5f)
                horizontalLineTo(21f)
            }
            path(
                stroke = SolidColor(Color.Black),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(19f, 6f)
                verticalLineTo(20f)
                curveTo(19f, 20.53f, 18.789f, 21.039f, 18.414f, 21.414f)
                curveTo(18.039f, 21.789f, 17.53f, 22f, 17f, 22f)
                horizontalLineTo(7f)
                curveTo(6.47f, 22f, 5.961f, 21.789f, 5.586f, 21.414f)
                curveTo(5.211f, 21.039f, 5f, 20.53f, 5f, 20f)
                verticalLineTo(6f)
                moveTo(8f, 6f)
                verticalLineTo(4f)
                curveTo(8f, 3.47f, 8.211f, 2.961f, 8.586f, 2.586f)
                curveTo(8.961f, 2.211f, 9.47f, 2f, 10f, 2f)
                horizontalLineTo(14f)
                curveTo(14.53f, 2f, 15.039f, 2.211f, 15.414f, 2.586f)
                curveTo(15.789f, 2.961f, 16f, 3.47f, 16f, 4f)
                verticalLineTo(6f)
            }
            path(
                stroke = SolidColor(Color.Black),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(10f, 11f)
                verticalLineTo(17f)
            }
            path(
                stroke = SolidColor(Color.Black),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(14f, 11f)
                verticalLineTo(17f)
            }
        }.build()

        return _Delete!!
    }

@Suppress("ObjectPropertyName")
private var _Delete: ImageVector? = null
