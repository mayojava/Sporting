package com.mobile.app.sporting

import androidx.compose.animation.animatedFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.transition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.onActive
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.drawBehind
import androidx.compose.ui.drawLayer
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.platform.DensityAmbient
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mobile.app.sporting.ui.*
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun SelectionDetails(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        ConstraintLayout(
                modifier = Modifier.fillMaxSize()
                        .background(color = surfaceColor)
        ) {
            val (
                    headerRef,
                    buttonRef,
                    gapRef,
                    itemsRef) = createRefs()

            Header(modifier = Modifier.constrainAs(headerRef) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }, navController = navController)

            Spacer(
                    modifier = Modifier.height(80.dp).width(6.dp)
                            .constrainAs(gapRef) {
                                start.linkTo(parent.start)
                                top.linkTo(headerRef.bottom)
                            })

            Column(
                    modifier = Modifier.fillMaxWidth()
                            .constrainAs(itemsRef) {
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                top.linkTo(gapRef.bottom)
                                bottom.linkTo(buttonRef.top, margin = 24.dp)
                            }
            ) {
                mockItems.forEach {
                    ItemRow(data = it, isHighlighted = it.row == 2)
                }
            }

            BottomButton(modifier = Modifier.constrainAs(buttonRef) {
                bottom.linkTo(parent.bottom, margin = 24.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })
        }

        val cardState = transition(
            definition = detailsGraphTransitionDef, toState = "stop", initState = "start")
        GraphCard(
                modifier = Modifier
                        .align(Alignment.TopCenter)
                        .fillMaxWidth(0.85f)
                        .offset(y = 200.dp)
                        .height(180.dp)
                    .drawLayer(
                        cameraDistance = 40f,
                        rotationX = cardState[detailsGraphRotationX]
                    )
        )
    }
}

@Composable
fun GraphCard(
        modifier: Modifier = Modifier
) {
    Surface(
            shape = RoundedCornerShape(16.dp),
            color = graphCardBg,
            elevation = 4.dp,
            modifier = modifier
    ) {

    }
}

@Composable
fun BottomButton(
        modifier: Modifier = Modifier
) {
    val buttonOffset = animatedFloat(initVal = 0f)
    val iconBgAlpha = animatedFloat(initVal = 0f)
    val iconScale = animatedFloat(initVal = 1f)

    onActive{
        buttonOffset.animateTo(1f, tween(durationMillis = 1000, delayMillis = 200))
        iconScale.animateTo(0.1f, tween(durationMillis = 800, delayMillis = 400)) {_, _ ->
            iconScale.animateTo(1f, tween(durationMillis = 100))
        }
    }

    Box(
            modifier = modifier
                    .clickable(onClick = {})
                    .offset(y = lerp(120.dp, 0.dp, buttonOffset.value))
                    .background(color = seeMoreBg, shape = RoundedCornerShape(32.dp))
                    .fillMaxWidth(0.6f)
                    .padding(start = 24.dp, end = 8.dp)
                    .padding(vertical = 16.dp)
    ) {
        Text(
                text = "See more",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterStart)
        )
        Box(
                modifier = Modifier.size(32.dp)
                        .align(Alignment.CenterEnd)
                        .background(Color.White, shape = CircleShape)
        ) {
            Icon(
                    Icons.Default.KeyboardArrowRight,
                    tint = seeMoreBg,
                    modifier = Modifier.align(Alignment.Center).drawLayer(scaleX = iconScale.value, scaleY = iconScale.value)
            )
        }
    }
}

@Composable
fun Header(
        modifier: Modifier = Modifier,
        navController: NavController
) {
    val imgUrl = "https://i.pravatar.cc/200?img=30"
    val bgFloat = animatedFloat(initVal = 1f)
    val avatarFloat = animatedFloat(initVal = 0f)
    val counterBgAnim = animatedFloat(initVal = 0f)
    val counterOpacity = animatedFloat(initVal = 0f)
    val counterTextAnim = animatedFloat(initVal = 1f)

    onActive {
        bgFloat.animateTo(0f, tween(durationMillis = 800))
        avatarFloat.animateTo(1f, tween(durationMillis = 1000))
        counterBgAnim.animateTo(1f, tween(durationMillis = 500)) { _, _ ->
            counterOpacity.snapTo(1f)
        }
        counterTextAnim.animateTo(0f, tween(durationMillis = 400, delayMillis = 450)) { _, _ ->
            counterTextAnim.animateTo(1f)
        }
    }

    Box(
            modifier = modifier
                    .height(300.dp)
                    .drawLayer(translationY = with(DensityAmbient.current) { -320.dp.toPx() * bgFloat.value })
                    .background(
                            color = selectionHeaderBgColor,
                            shape = RoundedCornerShape(bottomRight = 32.dp, bottomLeft = 32.dp))
    ) {
        IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.padding(top = 36.dp)
        ) {
            Icon(Icons.Default.ArrowBack, tint = Color.White)
        }

        Column(
                modifier = Modifier
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
                                .offset(y = lerp(-32.dp, 0.dp, avatarFloat.value))
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
                                    .offset(x = -2.dp, y = -2.dp)
                                    .size(24.dp)
                                    .drawLayer(alpha = counterOpacity.value)
                                    .background(color = Color.White, shape = CircleShape)
                                    .align(Alignment.BottomEnd)

                    ) {
                        Text(
                                text = "23",
                                color = headerCounterTextColor,
                                modifier = Modifier.align(Alignment.Center)
                                        .drawLayer(scaleX = counterTextAnim.value, scaleY = counterTextAnim.value),
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 12.sp,
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

@Composable
fun ItemRow(
        data: ItemRowData,
        isHighlighted: Boolean,
        modifier: Modifier = Modifier
) {
    Surface(
            color = if (isHighlighted) graphCardBg else surfaceColor,
            shape = if (isHighlighted) RoundedCornerShape(topRight = 56.dp) else RectangleShape,
            modifier = modifier.fillMaxWidth()
    ) {
        Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp).padding(end = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(modifier = Modifier.size(8.dp))
            RowNumber(isHighlighted = isHighlighted, data.row)
            Column(horizontalAlignment = Alignment.Start) {

                Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = data.country, color = textColorOne)
                    Icon(
                            Icons.Default.Star,
                            tint = if (isHighlighted) selectedIconColor else unselectedColor,
                    )
                }
                Spacer(modifier = Modifier.size(2.dp))
                Text(
                        text = data.title,
                        color = textColorTwo,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W500
                )
                Spacer(modifier = Modifier.size(2.dp))
                Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = data.price, color = textColorOne)
                    Text(
                            text = "more",
                            color = if (isHighlighted) selectedColor else unselectedColor,
                            textDecoration = TextDecoration.Underline
                    )
                }
            }
        }
    }
}

@Composable
fun RowNumber(isHighlighted: Boolean, row: Int) {

    if (isHighlighted) {
        StyledRowNumber(width = 24.dp, height = 48.dp) {
            Text(
                    text = "$row",
                    modifier = Modifier.align(Alignment.Center),
                    color = rowNumberTextColor
            )
        }
    } else {
        RegularRowNumber(row = row)
    }
}

@Composable
fun RegularRowNumber(row: Int) {
    Box(
            modifier = Modifier
                    .size(32.dp)
                    .background(color = unselectedColor, shape = CircleShape)
    ) {
        Text(text = "$row", color = rowNumberTextColor, modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
fun StyledRowNumber(
        width: Dp,
        height: Dp,
        content: @Composable BoxScope.() -> Unit
) {
    Box(
            modifier = Modifier
                    .width(width)
                    .height(height)
                    .drawIcon(bgColor = selectedColor)
    ) {
        content()
    }
}

fun Modifier.drawIcon(bgColor: Color) = this.drawBehind {
    val paint = Paint().apply {
        isAntiAlias = true
        color = bgColor
    }

    drawIntoCanvas { canvas ->
        val arcHeight = size.height / 4

        val rect = Rect(
                topLeft = Offset(0f, arcHeight),
                bottomRight = Offset(size.width, size.height - arcHeight)
        )

        canvas.drawRect(rect, paint)

        canvas.drawArc(
                left = 0f,
                top = 0f,
                right = size.width,
                bottom = arcHeight * 2,
                startAngle = 360f,
                sweepAngle = -180f,
                useCenter = false, paint = paint
        )

        canvas.drawArc(
                left = 0f,
                top = size.height - (arcHeight * 2),
                right = size.width,
                bottom = size.height,
                startAngle = 0f,
                sweepAngle = 180f,
                useCenter = false,
                paint = paint
        )
    }
}

data class ItemRowData(
        val row: Int,
        val country: String,
        val title: String,
        val price: String
)

val mockItems = listOf(
        ItemRowData(1, "Australia", "Rent Surfing on Sydney's", "from $61.00"),
        ItemRowData(2, "USA", "Rent Surfing on California", "from $72.00")
)