package com.lookmyup.app.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

object AppIcons {
    val Comment: ImageVector
        get() {
            if (_Comment != null) {
                return _Comment!!
            }
            _Comment = ImageVector.Builder(
                name = "Comment",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 960f,
                viewportHeight = 960f
            ).apply {
                path(
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1.0f,
                    stroke = null,
                    strokeAlpha = 1.0f,
                    strokeLineWidth = 1.0f,
                    strokeLineCap = StrokeCap.Butt,
                    strokeLineJoin = StrokeJoin.Miter,
                    strokeLineMiter = 1.0f,
                    pathFillType = PathFillType.NonZero
                ) {
                    moveTo(240f, 560f)
                    horizontalLineToRelative(480f)
                    verticalLineToRelative(-80f)
                    horizontalLineTo(240f)
                    close()
                    moveToRelative(0f, -120f)
                    horizontalLineToRelative(480f)
                    verticalLineToRelative(-80f)
                    horizontalLineTo(240f)
                    close()
                    moveToRelative(0f, -120f)
                    horizontalLineToRelative(480f)
                    verticalLineToRelative(-80f)
                    horizontalLineTo(240f)
                    close()
                    moveTo(880f, 880f)
                    lineTo(720f, 720f)
                    horizontalLineTo(160f)
                    quadToRelative(-33f, 0f, -56.5f, -23.5f)
                    reflectiveQuadTo(80f, 640f)
                    verticalLineToRelative(-480f)
                    quadToRelative(0f, -33f, 23.5f, -56.5f)
                    reflectiveQuadTo(160f, 80f)
                    horizontalLineToRelative(640f)
                    quadToRelative(33f, 0f, 56.5f, 23.5f)
                    reflectiveQuadTo(880f, 160f)
                    close()
                    moveTo(160f, 640f)
                    horizontalLineToRelative(594f)
                    lineToRelative(46f, 45f)
                    verticalLineToRelative(-525f)
                    horizontalLineTo(160f)
                    close()
                    moveToRelative(0f, 0f)
                    verticalLineToRelative(-480f)
                    close()
                }
            }.build()
            return _Comment!!
        }

    private var _Comment: ImageVector? = null

    val Bookmark: ImageVector
        get() {
            if (_Bookmark != null) {
                return _Bookmark!!
            }
            _Bookmark = ImageVector.Builder(
                name = "Bookmark",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 960f,
                viewportHeight = 960f
            ).apply {
                path(
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1.0f,
                    stroke = null,
                    strokeAlpha = 1.0f,
                    strokeLineWidth = 1.0f,
                    strokeLineCap = StrokeCap.Butt,
                    strokeLineJoin = StrokeJoin.Miter,
                    strokeLineMiter = 1.0f,
                    pathFillType = PathFillType.NonZero
                ) {
                    moveTo(480f, 720f)
                    lineToRelative(-168f, 72f)
                    quadToRelative(-40f, 17f, -76f, -6.5f)
                    reflectiveQuadTo(200f, 719f)
                    verticalLineToRelative(-519f)
                    quadToRelative(0f, -33f, 23.5f, -56.5f)
                    reflectiveQuadTo(280f, 120f)
                    horizontalLineToRelative(400f)
                    quadToRelative(33f, 0f, 56.5f, 23.5f)
                    reflectiveQuadTo(760f, 200f)
                    verticalLineToRelative(519f)
                    quadToRelative(0f, 43f, -36f, 66.5f)
                    reflectiveQuadToRelative(-76f, 6.5f)
                    close()
                    moveToRelative(0f, -88f)
                    lineToRelative(200f, 86f)
                    verticalLineToRelative(-518f)
                    horizontalLineTo(280f)
                    verticalLineToRelative(518f)
                    close()
                    moveToRelative(0f, -432f)
                    horizontalLineTo(280f)
                    horizontalLineToRelative(400f)
                    close()
                }
            }.build()
            return _Bookmark!!
        }

    private var _Bookmark: ImageVector? = null

    val Bookmark_check: ImageVector
        get() {
            if (_Bookmark_check != null) {
                return _Bookmark_check!!
            }
            _Bookmark_check = ImageVector.Builder(
                name = "Bookmark_check",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 960f,
                viewportHeight = 960f
            ).apply {
                path(
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1.0f,
                    stroke = null,
                    strokeAlpha = 1.0f,
                    strokeLineWidth = 1.0f,
                    strokeLineCap = StrokeCap.Butt,
                    strokeLineJoin = StrokeJoin.Miter,
                    strokeLineMiter = 1.0f,
                    pathFillType = PathFillType.NonZero
                ) {
                    moveTo(438f, 447f)
                    lineToRelative(-29f, -29f)
                    quadToRelative(-12f, -11f, -28f, -11f)
                    reflectiveQuadToRelative(-28f, 12f)
                    reflectiveQuadToRelative(-12f, 28f)
                    reflectiveQuadToRelative(12f, 28f)
                    lineToRelative(56f, 57f)
                    quadToRelative(12f, 12f, 28.5f, 12f)
                    reflectiveQuadToRelative(28.5f, -12f)
                    lineToRelative(141f, -142f)
                    quadToRelative(12f, -12f, 12f, -28f)
                    reflectiveQuadToRelative(-12f, -28f)
                    reflectiveQuadToRelative(-28f, -12f)
                    reflectiveQuadToRelative(-28f, 12f)
                    close()
                    moveToRelative(42f, 273f)
                    lineToRelative(-168f, 72f)
                    quadToRelative(-40f, 17f, -76f, -6.5f)
                    reflectiveQuadTo(200f, 719f)
                    verticalLineToRelative(-519f)
                    quadToRelative(0f, -33f, 23.5f, -56.5f)
                    reflectiveQuadTo(280f, 120f)
                    horizontalLineToRelative(400f)
                    quadToRelative(33f, 0f, 56.5f, 23.5f)
                    reflectiveQuadTo(760f, 200f)
                    verticalLineToRelative(519f)
                    quadToRelative(0f, 43f, -36f, 66.5f)
                    reflectiveQuadToRelative(-76f, 6.5f)
                    close()
                    moveToRelative(0f, -88f)
                    lineToRelative(200f, 86f)
                    verticalLineToRelative(-518f)
                    horizontalLineTo(280f)
                    verticalLineToRelative(518f)
                    close()
                    moveToRelative(0f, -432f)
                    horizontalLineTo(280f)
                    horizontalLineToRelative(400f)
                    close()
                }
            }.build()
            return _Bookmark_check!!
        }

    private var _Bookmark_check: ImageVector? = null


    val Autoplay: ImageVector
        get() {
            if (_Autoplay != null) {
                return _Autoplay!!
            }
            _Autoplay = ImageVector.Builder(
                name = "Autoplay",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 960f,
                viewportHeight = 960f
            ).apply {
                path(
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1.0f,
                    stroke = null,
                    strokeAlpha = 1.0f,
                    strokeLineWidth = 1.0f,
                    strokeLineCap = StrokeCap.Butt,
                    strokeLineJoin = StrokeJoin.Miter,
                    strokeLineMiter = 1.0f,
                    pathFillType = PathFillType.NonZero
                ) {
                    moveTo(380f, 660f)
                    verticalLineToRelative(-360f)
                    lineToRelative(280f, 180f)
                    close()
                    moveTo(480f, 920f)
                    quadToRelative(-108f, 0f, -202.5f, -49.5f)
                    reflectiveQuadTo(120f, 732f)
                    verticalLineToRelative(108f)
                    horizontalLineTo(40f)
                    verticalLineToRelative(-240f)
                    horizontalLineToRelative(240f)
                    verticalLineToRelative(80f)
                    horizontalLineToRelative(-98f)
                    quadToRelative(51f, 75f, 129.5f, 117.5f)
                    reflectiveQuadTo(480f, 840f)
                    quadToRelative(115f, 0f, 208.5f, -66f)
                    reflectiveQuadTo(820f, 599f)
                    lineToRelative(78f, 18f)
                    quadToRelative(-45f, 136f, -160f, 219.5f)
                    reflectiveQuadTo(480f, 920f)
                    moveTo(42f, 440f)
                    quadToRelative(7f, -67f, 32f, -128.5f)
                    reflectiveQuadTo(143f, 198f)
                    lineToRelative(57f, 57f)
                    quadToRelative(-32f, 41f, -52f, 87.5f)
                    reflectiveQuadTo(123f, 440f)
                    close()
                    moveToRelative(214f, -241f)
                    lineToRelative(-57f, -57f)
                    quadToRelative(53f, -44f, 114f, -69.5f)
                    reflectiveQuadTo(440f, 42f)
                    verticalLineToRelative(80f)
                    quadToRelative(-51f, 5f, -97f, 25f)
                    reflectiveQuadToRelative(-87f, 52f)
                    moveToRelative(449f, 0f)
                    quadToRelative(-41f, -32f, -87.5f, -52f)
                    reflectiveQuadTo(520f, 122f)
                    verticalLineToRelative(-80f)
                    quadToRelative(67f, 6f, 128.5f, 31f)
                    reflectiveQuadTo(762f, 142f)
                    close()
                    moveToRelative(133f, 241f)
                    quadToRelative(-5f, -51f, -25f, -97.5f)
                    reflectiveQuadTo(761f, 255f)
                    lineToRelative(57f, -57f)
                    quadToRelative(44f, 52f, 69f, 113.5f)
                    reflectiveQuadTo(918f, 440f)
                    close()
                }
            }.build()
            return _Autoplay!!
        }

    private var _Autoplay: ImageVector? = null


}