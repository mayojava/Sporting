package com.mobile.app.sporting

import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.app.sporting.ui.*
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable fun SelectionDetails(
        modifier: Modifier = Modifier
) {
    val ctx = ContextAmbient.current
    Box(
            modifier = modifier.fillMaxSize()
                    .background(color = surfaceColor)
                    .padding(bottom = 32.dp)
    ) {
        Header(modifier = Modifier.fillMaxWidth())
        Surface(
                shape = RoundedCornerShape(16.dp),
                color = graphCardBg,
                elevation = 4.dp,
                modifier = Modifier.fillMaxWidth(0.85f)
                        .preferredHeight(180.dp)
                        .align(Alignment.Center)
                        .offset(y = -90.dp)
        ) {

        }
        
        Box(
                modifier = Modifier
                        .background(color = seeMoreBg, shape = RoundedCornerShape(32.dp))
                        .fillMaxWidth(0.6f)
                        .align(Alignment.BottomCenter)
                        .padding(start = 24.dp, end = 8.dp)
                        .padding(vertical = 16.dp)
                        .clickable(onClick = {})
        ) {
            Text(
                    text = "See more",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterStart)
            )
            Box(modifier = Modifier.size(32.dp)
                    .align(Alignment.CenterEnd)
                    .background(Color.White, shape = CircleShape)) {
                Icon(
                        Icons.Default.KeyboardArrowRight,
                        tint = seeMoreBg,
                        modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Composable fun Header(
        modifier: Modifier = Modifier
) {
    val imgUrl = "https://i.pravatar.cc/200?img=30"

    Box(modifier = modifier
            .fillMaxHeight(0.4f)
            .background(
                    color = selectionHeaderBgColor,
                    shape = RoundedCornerShape(bottomRight = 32.dp, bottomLeft = 32.dp))
    ) {
        IconButton(onClick = {}, modifier = Modifier.padding(top = 36.dp)) {
            Icon(Icons.Default.ArrowBack, tint = Color.White)
        }

        Column(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 36.dp)
                .padding(horizontal = 16.dp),
        ) {
            Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                        modifier = Modifier.size(84.dp)
                                .background(imageBgColor, shape = CircleShape)
                ) {
                    CoilImage(
                            data = imgUrl,
                            modifier = Modifier.size(78.dp)
                                    .clip(CircleShape)
                                    .align(Alignment.Center)
                    )

                    Box(
                            modifier = Modifier
                                    .offset(x=-2.dp, y = -2.dp)
                                    .size(24.dp)
                                    .background(color = Color.White, shape = CircleShape)
                                    .align(Alignment.BottomEnd)

                    ) {
                        Text(
                                text = "23",
                                color = Color.Black,
                                modifier = Modifier.align(Alignment.Center),
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
                Text(
                        text = "Gabriella Estrada",
                        color = selectionHeaderText,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                )
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                    text = "Rent Surfing",
                    color = selectionHeaderText,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
            )
        }
    }
}