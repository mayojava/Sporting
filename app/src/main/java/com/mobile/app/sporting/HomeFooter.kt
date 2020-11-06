package com.mobile.app.sporting

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableRow
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageAsset
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

val mockSurfers = listOf(
        R.drawable.surfer_one,
        R.drawable.surfer_two,
        R.drawable.surfer_three,
        R.drawable.surfer_four
)

@Composable fun Footer(
    surfers: List<Int> = mockSurfers,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
                .background(shape = RoundedCornerShape(topRight = 64.dp), color = Color(0xffff0151))
                .padding(vertical = 24.dp)
                .padding(start = 24.dp)
                .preferredHeight(180.dp)
    ) {
        Box {
            Text(
                    text = "Rental equipment",
                    style = MaterialTheme.typography.h3,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFf0004c)
            )

            Text(
                    text = "Rental equipment",
                    color = Color.White,
                    style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Bold)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
        ScrollableRow {
            surfers.forEach { surfer ->
                Surface(
                        shape = RoundedCornerShape(16.dp)
                ) {
                    Image(
                            asset = imageResource(id = surfer),
                            modifier = Modifier.size(140.dp)
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}