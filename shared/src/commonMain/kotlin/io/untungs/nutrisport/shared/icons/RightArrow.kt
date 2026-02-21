package io.untungs.nutrisport.shared.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icon.RightArrow: ImageVector
    get() {
        if (_RightArrow != null) {
            return _RightArrow!!
        }
        _RightArrow = ImageVector.Builder(
            name = "RightArrow",
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
                moveTo(15f, 10f)
                lineTo(20f, 15f)
                lineTo(15f, 20f)
            }
            path(
                stroke = SolidColor(Color.Black),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(4f, 4f)
                verticalLineTo(11f)
                curveTo(4f, 12.061f, 4.421f, 13.078f, 5.172f, 13.828f)
                curveTo(5.922f, 14.579f, 6.939f, 15f, 8f, 15f)
                horizontalLineTo(20f)
            }
        }.build()

        return _RightArrow!!
    }

@Suppress("ObjectPropertyName")
private var _RightArrow: ImageVector? = null
