package com.mobile.app.sporting.ui

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