package io.untungs.nutrisport.core.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icon.VerticalMenu: ImageVector
    get() {
        if (_VerticalMenu != null) {
            return _VerticalMenu!!
        }
        _VerticalMenu = ImageVector.Builder(
            name = "VerticalMenu",
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
                moveTo(12f, 13f)
                curveTo(12.552f, 13f, 13f, 12.552f, 13f, 12f)
                curveTo(13f, 11.448f, 12.552f, 11f, 12f, 11f)
                curveTo(11.448f, 11f, 11f, 11.448f, 11f, 12f)
                curveTo(11f, 12.552f, 11.448f, 13f, 12f, 13f)
                close()
            }
            path(
                stroke = SolidColor(Color.Black),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(12f, 6f)
                curveTo(12.552f, 6f, 13f, 5.552f, 13f, 5f)
                curveTo(13f, 4.448f, 12.552f, 4f, 12f, 4f)
                curveTo(11.448f, 4f, 11f, 4.448f, 11f, 5f)
                curveTo(11f, 5.552f, 11.448f, 6f, 12f, 6f)
                close()
            }
            path(
                stroke = SolidColor(Color.Black),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(12f, 20f)
                curveTo(12.552f, 20f, 13f, 19.552f, 13f, 19f)
                curveTo(13f, 18.448f, 12.552f, 18f, 12f, 18f)
                curveTo(11.448f, 18f, 11f, 18.448f, 11f, 19f)
                curveTo(11f, 19.552f, 11.448f, 20f, 12f, 20f)
                close()
            }
        }.build()

        return _VerticalMenu!!
    }

@Suppress("ObjectPropertyName")
private var _VerticalMenu: ImageVector? = null
