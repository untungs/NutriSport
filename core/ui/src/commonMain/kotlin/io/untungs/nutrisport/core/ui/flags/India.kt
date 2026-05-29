package io.untungs.nutrisport.core.ui.flags

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.PathData
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Flags.India: ImageVector
    get() {
        if (_India != null) {
            return _India!!
        }
        _India = ImageVector.Builder(
            name = "India",
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
            }
            group(
                clipPathData = PathData {
                    moveTo(-4f, 0f)
                    verticalLineTo(24f)
                    horizontalLineTo(28f)
                    verticalLineTo(0f)
                    horizontalLineTo(-4f)
                    close()
                }
            ) {
                path(
                    fill = SolidColor(Color(0xFFFF8C1A)),
                    pathFillType = PathFillType.EvenOdd
                ) {
                    moveTo(-4f, 0f)
                    verticalLineTo(8f)
                    horizontalLineTo(28f)
                    verticalLineTo(0f)
                    horizontalLineTo(-4f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF5EAA22)),
                    pathFillType = PathFillType.EvenOdd
                ) {
                    moveTo(-4f, 16f)
                    verticalLineTo(24f)
                    horizontalLineTo(28f)
                    verticalLineTo(16f)
                    horizontalLineTo(-4f)
                    close()
                }
                path(fill = SolidColor(Color(0xFF3D58DB))) {
                    moveTo(12f, 8f)
                    curveTo(14.209f, 8f, 16f, 9.791f, 16f, 12f)
                    curveTo(16f, 14.209f, 14.209f, 16f, 12f, 16f)
                    curveTo(9.791f, 16f, 8f, 14.209f, 8f, 12f)
                    curveTo(8f, 9.791f, 9.791f, 8f, 12f, 8f)
                    close()
                    moveTo(12f, 9f)
                    curveTo(10.343f, 9f, 9f, 10.343f, 9f, 12f)
                    curveTo(9f, 13.657f, 10.343f, 15f, 12f, 15f)
                    curveTo(13.657f, 15f, 15f, 13.657f, 15f, 12f)
                    curveTo(15f, 10.343f, 13.657f, 9f, 12f, 9f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF3D58DB)),
                    pathFillType = PathFillType.EvenOdd
                ) {
                    moveTo(11.995f, 12.861f)
                    lineTo(11.424f, 15.981f)
                    lineTo(11.755f, 12.826f)
                    lineTo(10.328f, 15.66f)
                    lineTo(11.535f, 12.726f)
                    lineTo(9.368f, 15.042f)
                    lineTo(11.352f, 12.568f)
                    lineTo(8.62f, 14.18f)
                    lineTo(11.222f, 12.364f)
                    lineTo(8.146f, 13.141f)
                    lineTo(11.153f, 12.132f)
                    lineTo(7.983f, 12.011f)
                    lineTo(11.153f, 11.89f)
                    lineTo(8.146f, 10.881f)
                    lineTo(11.222f, 11.658f)
                    lineTo(8.62f, 9.843f)
                    lineTo(11.352f, 11.455f)
                    lineTo(9.368f, 8.98f)
                    lineTo(11.535f, 11.296f)
                    lineTo(10.328f, 8.362f)
                    lineTo(11.755f, 11.196f)
                    lineTo(11.424f, 8.041f)
                    lineTo(11.995f, 11.161f)
                    lineTo(12.565f, 8.041f)
                    lineTo(12.234f, 11.196f)
                    lineTo(13.661f, 8.362f)
                    lineTo(12.454f, 11.296f)
                    lineTo(14.621f, 8.98f)
                    lineTo(12.637f, 11.455f)
                    lineTo(15.369f, 9.843f)
                    lineTo(12.767f, 11.658f)
                    lineTo(15.843f, 10.881f)
                    lineTo(12.835f, 11.89f)
                    lineTo(16.006f, 12.011f)
                    lineTo(12.835f, 12.132f)
                    lineTo(15.843f, 13.141f)
                    lineTo(12.767f, 12.364f)
                    lineTo(15.369f, 14.18f)
                    lineTo(12.637f, 12.568f)
                    lineTo(14.621f, 15.042f)
                    lineTo(12.454f, 12.726f)
                    lineTo(13.661f, 15.66f)
                    lineTo(12.234f, 12.826f)
                    lineTo(12.565f, 15.981f)
                    lineTo(11.995f, 12.861f)
                    close()
                }
            }
        }.build()

        return _India!!
    }

@Suppress("ObjectPropertyName")
private var _India: ImageVector? = null
