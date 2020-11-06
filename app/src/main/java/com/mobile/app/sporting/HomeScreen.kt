package com.mobile.app.sporting

import androidx.compose.animation.*
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.navigate
import com.mobile.app.sporting.ui.*

@Composable
fun HomeScreen(navController: NavController) {
    var selectedIndex by remember { mutableStateOf(0) }

    Box(
            Modifier.fillMaxSize()
    ) {
        ScrollableColumn(
                Modifier.fillMaxSize()
                        .background(color = Color(0xfff8f9f8))
                        .padding(horizontal = 16.dp)
        ) {
            val ratingTransition = transition(
                    definition = ratingTransDef,
                    toState = "end",
                    initState = "start"
            )
            Text(
                    text = "Rating",
                    color = Color(0xffcbcccb),
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(top = 24.dp)
                            .offset(y = ratingTransition[ratingOffset])
            )

            val topActTransition = transition(
                    definition = topActivityTransDef,
                    toState = "end",
                    initState = "start"
            )
            Row(
                    Modifier.fillMaxWidth()
                            .offset(y = topActTransition[topActivityOffset]),
                    verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                        text = "Top Activity",
                        color = Color(0xff2f2e38),
                        style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier.weight(1f)
                )

                Icon(
                        Icons.Outlined.CalendarToday,
                        tint = Color(0xff2f2e38)
                )
            }

            Spacer(modifier = Modifier.height(48.dp))

            ChipsLayout(
                    modifier = Modifier.fillMaxWidth(),
                    itemCount = 3,
                    selectedIndex = selectedIndex) {

                listOf("Surfing", "Snowboard", "Skiing").forEachIndexed { index, title ->
                    if (index == selectedIndex) {
                        SelectedChip(label = title)
                    } else {
                        UnselectedChip(
                                label = title,
                                index = index,
                                onSelectedIndexChange = { selectedIndex = it }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.preferredHeight(32.dp))

            Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                        Modifier.weight(1f)
                ) {
                    val state = transition(
                            definition = textValueTransDef,
                            toState = "end",
                            initState = "start"
                    )
                    val text = String.format("%.3f", state[propKey])
                    Text(
                            text = text,
                            color = Color(0xff2f2e38),
                            style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.Bold)
                    )

                    Text(
                            text = "activity",
                            color = Color(0xffcbcccb)
                    )
                }

                Text(
                        text = "profile",
                        color = Color(0xff2f2e38),
                        style = TextStyle(
                                fontWeight = FontWeight.W600,
                                fontSize = 18.sp,
                                letterSpacing = 1.sp
                        )
                )
                Spacer(modifier = Modifier.preferredWidth(6.dp))
                Icon(Icons.Outlined.Person, tint = Color(0xff2f2e38))
            }
            Spacer(modifier = Modifier.height(16.dp))
            WeeklyChart()
            Spacer(modifier = Modifier.preferredHeight(32.dp))
            PeriodRow(navController)
            Spacer(modifier = Modifier.preferredHeight(48.dp))
        }

        Footer(
                modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
        )
    }
}

@Composable fun ChipsLayout(
        modifier: Modifier,
        itemCount: Int,
        selectedIndex: Int,
        children: @Composable () -> Unit
) {
    Layout(
            modifier = modifier,
            children = children
    ) { measurables, constraints ->
        val unselectedWidth = constraints.maxWidth / (itemCount + 1)
        val selectedWidth = unselectedWidth + (unselectedWidth*0.5).toInt()

        var selectedItemHeight = 0
        val place = measurables.mapIndexed { index, measurable ->
            if (index == selectedIndex) {
                measurable.measure(constraints = constraints.copy(
                        minWidth = selectedWidth,
                        maxWidth = selectedWidth
                )).also { selectedItemHeight = it.height }
            } else {
                measurable.measure(constraints = constraints.copy(
                        minWidth = unselectedWidth,
                        maxWidth = unselectedWidth
                ))
            }
        }

        layout(
                constraints.maxWidth,
                place.maxByOrNull { it.height }?.height ?: 0
        ) {
            var xPos = 0
            place.forEachIndexed { index, placeable ->
                if (index == selectedIndex) {
                    placeable.placeRelative(xPos, 0)
                } else {
                    val yPos = (selectedItemHeight - placeable.height)/2
                    placeable.placeRelative(xPos, yPos)
                }
                xPos += placeable.width
            }
        }
    }
}

@Composable fun UnselectedChip(
        label: String,
        index: Int,
        onSelectedIndexChange: (Int) -> Unit
) {
    Text(
            text = label,
            color = Color(0xffcbcccb),
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                    .clickable(onClick = { onSelectedIndexChange(index) })
                    //.background(color = Color.Green)
    )
}

@Composable fun SelectedChip(label: String) {
    Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color(0xffffbd00),
            modifier = Modifier,
            elevation = 4.dp,
    ) {
        Text(
                text = label,
                textAlign = TextAlign.Center,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}

@Composable
fun WeeklyChart(
        level: List<Float> = listOf(),
        modifier: Modifier = Modifier
) {
    val transition = transition(
            definition = weekChartTransitionDef,
            toState = "end",
            initState = "start"
    )

    Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color(0xFFfefffe),
            modifier = Modifier.fillMaxWidth()
                    .preferredHeight(180.dp),
            elevation = 4.dp
    ) {

    }
}

@Composable
fun PeriodRow(navController: NavController) {
    Row(
            Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
    ) {
        Period(label = "Day")
        Period(label = "Week", isSelected = true)
        Period(label = "Month")

        IconButton(onClick = { navController.navigate("details") }) {
            val state = transition(
                    definition = iconTransitionDefinition,
                    toState = 2,
                    initState = 1,
            )
            val icp = state[iconProp]
            
            Box(
                    modifier = Modifier.size(64.dp)
                            .background(color = Color(0xff2f2e38), shape = CircleShape)
                            .drawLayer(
                                    scaleY = icp,
                                    scaleX = icp
                            ),
                    alignment = Alignment.Center
            ) {
                Icon(Icons.Default.Add)
            }
        }
    }
}

@Composable
fun Period(
        label: String,
        isSelected: Boolean = false,
        modifier: Modifier = Modifier,
) {
    Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
                text = label,
                fontSize = 16.sp,
                fontWeight = if (isSelected) FontWeight.W600 else FontWeight.Normal,
                color = if (isSelected) Color(0xff2f2e38) else Color(0xffcbcccb)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Box(
                modifier = Modifier.size(8.dp)
                        .background(color = if (isSelected) Color(0xff2f2e38) else Color(0xffcbcccb), shape = CircleShape),
        )
    }
}