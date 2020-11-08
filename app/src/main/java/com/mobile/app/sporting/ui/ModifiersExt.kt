package com.mobile.app.sporting.ui

import androidx.compose.animation.animatedFloat
import androidx.compose.animation.core.AnimatedFloat
import androidx.compose.animation.core.tween
import androidx.compose.runtime.onActive
import androidx.compose.runtime.remember
import androidx.compose.ui.*
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas

fun Modifier.shimmer() = composed  {
    val progress = animatedFloat(initVal = 0f)
    onActive {
        progress.animateTo(1f, tween(durationMillis = 2000))
        onDispose {
            progress.stop()
        }
    }
    remember { ShimmerModifier(progress) }
}

private class ShimmerModifier(val t: AnimatedFloat): DrawModifier {
    private val shaderColors = listOf(
        Color(0xAAAAAA) ,
        Color(0xa2AAAAAA),
        Color(0xAAAAAA)
    )

    private val paint = Paint().apply {
        isAntiAlias = true
        style = PaintingStyle.Stroke
        color = Color(0xefefef)
    }

    override fun ContentDrawScope.draw() {
        paint.shader = LinearGradientShader(
            size.toRect().topLeft,
            size.toRect().bottomRight,
            shaderColors,
            listOf(0f, t.value, 1f)
        )

        drawIntoCanvas {
            it.drawRect(rect = size.toRect(), paint = paint)
        }
    }
}
