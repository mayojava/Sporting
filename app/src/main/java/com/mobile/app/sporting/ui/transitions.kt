package com.mobile.app.sporting.ui

import androidx.compose.animation.ColorPropKey
import androidx.compose.animation.DpPropKey
import androidx.compose.animation.core.*
import androidx.compose.ui.unit.dp

val propKey = FloatPropKey()
val textValueTransDef = transitionDefinition<String> {
    state("start") {
        this[propKey] = 0.0f
    }

    state("end") {
        this[propKey] = 2.536f
    }

    transition("start" to "end") {
        propKey using keyframes {
            durationMillis = 400
            0.0f at 0
            1.5f at 150 with FastOutLinearInEasing
            2.0f at 250 with FastOutSlowInEasing
            2.536f at 400 with FastOutSlowInEasing
        }
    }
}

val topActivityOffset = DpPropKey()
val topActivityTransDef = transitionDefinition<String> {
    state("start") {
        this[topActivityOffset] = -32.dp
    }

    state("end") {
        this[topActivityOffset] = 0.dp
    }

    transition("start" to "end") {
        topActivityOffset using tween(
                durationMillis = 600,
                easing = LinearOutSlowInEasing
        )
    }
}

val ratingOffset = DpPropKey()
val ratingTransDef = transitionDefinition<String> {
    state("start") {
        this[ratingOffset] = -32.dp
    }

    state("end") {
        this[ratingOffset] = 0.dp
    }

    transition("start" to "end") {
        ratingOffset using tween(
                durationMillis = 800,
                easing = LinearOutSlowInEasing
        )
    }
}

val iconProp = FloatPropKey()
val iconTransitionDefinition = transitionDefinition<Int> {
    state(1) {
        this[iconProp] = 1f
    }

    state(2) {
        this[iconProp] = 1f
    }

    transition(1 to 2) {
        iconProp using keyframes {
            durationMillis = 500
            1f at 0 with LinearOutSlowInEasing
            0.1f at 400 with LinearOutSlowInEasing
            1f at 500 with FastOutSlowInEasing
        }
    }
}

val rotationX = FloatPropKey()
val rotationZ = FloatPropKey()

val weekChartTransitionDef = transitionDefinition<String> {
    state("start") {
        this[rotationX] = 60.0f
        this[rotationZ] = -20f
    }

    state("end") {
        this[rotationX] = 0f
        this[rotationZ] = 0f
    }

    transition("start" to "end") {
        rotationX using tween(
                durationMillis = 1000,
                easing = LinearOutSlowInEasing
        )
    }
}

val barLoadPropKey = FloatPropKey()
val barLoadTransitionDef = transitionDefinition<Int> {
    state(1) {
        this[barLoadPropKey] = 1f
    }

    state(2) {
        this[barLoadPropKey] = 0f
    }

    transition(1 to 2) {
        barLoadPropKey using tween(
            durationMillis = 1500,
            easing = FastOutSlowInEasing//CubicBezierEasing(0.2f, 0.5f, 0.3f, 0.8f)
        )
    }
}