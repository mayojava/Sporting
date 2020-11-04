package com.mobile.app.sporting.linegraph

data class LineChartData(
    val points: List<Point>
)

data class Point(val xPos: Float, val yPos: Float)
