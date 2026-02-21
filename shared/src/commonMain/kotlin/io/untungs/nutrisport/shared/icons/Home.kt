package io.untungs.nutrisport.shared.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.PathData
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icon.Home: ImageVector
    get() {
        if (_Home != null) {
            return _Home!!
        }
        _Home = ImageVector.Builder(
            name = "Home",
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
                path(fill = SolidColor(Color.Black)) {
                    moveTo(6.001f, 19f)
                    horizontalLineTo(9.001f)
                    verticalLineTo(13f)
                    horizontalLineTo(15.001f)
                    verticalLineTo(19f)
                    horizontalLineTo(18.001f)
                    verticalLineTo(10f)
                    lineTo(12.001f, 5.5f)
                    lineTo(6.001f, 10f)
                    verticalLineTo(19f)
                    close()
                    moveTo(4.001f, 21f)
                    verticalLineTo(9f)
                    lineTo(12.001f, 3f)
                    lineTo(20.001f, 9f)
                    verticalLineTo(21f)
                    horizontalLineTo(13.001f)
                    verticalLineTo(15f)
                    horizontalLineTo(11.001f)
                    verticalLineTo(21f)
                    horizontalLineTo(4.001f)
                    close()
                }
            }
        }.build()

        return _Home!!
    }

@Suppress("ObjectPropertyName")
private var _Home: ImageVector? = null
