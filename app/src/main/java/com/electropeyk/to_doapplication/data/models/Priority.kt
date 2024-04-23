package com.electropeyk.to_doapplication.data.models

import androidx.compose.ui.graphics.Color
import com.electropeyk.to_doapplication.ui.theme.HighPriorityColor
import com.electropeyk.to_doapplication.ui.theme.LowPriorityColor
import com.electropeyk.to_doapplication.ui.theme.MediumPriorityColor
import com.electropeyk.to_doapplication.ui.theme.NonePriorityColor

enum class Priority(val color: Color) {
    HIGH(HighPriorityColor),
    MEDIUM(MediumPriorityColor),
    LOW(LowPriorityColor),
    NONE(NonePriorityColor),
}