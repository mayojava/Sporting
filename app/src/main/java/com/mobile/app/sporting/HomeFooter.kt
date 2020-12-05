package com.mobile.app.sporting

import androidx.compose.animation.animatedFloat
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableRow
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.onActive
import androidx.compose.ui.Modifier
import androidx.compose.ui.drawLayer
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.DensityAmbient
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val mockSurfers = listOf(
    R.drawable.surfer_one,
    R.drawable.surfer_two,
    R.drawable.surfer_three,
    R.drawable.surfer_four
)

@Composable
fun Footer(
    modifier: Modifier = Modifier,
    surfers: List<Int> = mockSurfers,
) {
    Column(
        modifier
            .background(shape = RoundedCornerShape(topRight = 64.dp), color = Color(0xffff0151))
            .padding(vertical = 24.dp)
            .padding(start = 24.dp)
            .preferredHeight(180.dp)
    ) {
        Box {
            val t = animatedFloat(initVal = 1f)
            val t2 = animatedFloat(initVal = 1f)
            val density = DensityAmbient.current

            onActive {
                t.animateTo(0f, tween(durationMillis = 800))
                t2.animateTo(0f, tween(durationMillis = 1000))
            }

            Text(
                text = "Rental equipment",
                style = MaterialTheme.typography.h4.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 38.sp,
                    letterSpacing = 1.5.sp
                ),
                color = Color(0xFFf0004c),
                modifier = Modifier.drawLayer(translationX = with(density) { 240.dp.toPx() * t2.value })
            )

            Text(
                text = "Rental equipment",
                color = Color.White,
                style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.drawLayer(
                    translationX = with(density) { -128.dp.toPx() * t.value }
                )
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
        ScrollableRow {
            surfers.forEach { surfer ->
                Surface(
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Image(
                        bitmap = imageResource(id = surfer),
                        modifier = Modifier.size(140.dp)
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}