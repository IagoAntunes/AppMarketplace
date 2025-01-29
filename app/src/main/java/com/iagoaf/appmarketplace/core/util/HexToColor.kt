package com.iagoaf.appmarketplace.core.util

import androidx.compose.ui.graphics.Color

abstract class HexToColor {
    companion object {
        fun toColor(code: String): Color {
            val cleanCode = code.replace("#", "")
            return Color(cleanCode.toLong(16) + 0xFF000000)
        }
    }
}