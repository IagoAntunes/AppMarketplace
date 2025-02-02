package com.iagoaf.appmarketplace.core.util

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class PhoneNumberVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = text.text.take(11)
        val formatted = StringBuilder()

        for (i in trimmed.indices) {
            when (i) {
                0 -> formatted.append("(")
                2 -> formatted.append(") ")
                7 -> formatted.append("-")
            }
            formatted.append(trimmed[i])
        }

        return TransformedText(
            AnnotatedString(formatted.toString()),
            object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    return when {
                        offset <= 0 -> offset
                        offset <= 2 -> offset + 1
                        offset <= 7 -> offset + 3
                        offset <= 11 -> offset + 4
                        else -> formatted.length
                    }
                }

                override fun transformedToOriginal(offset: Int): Int {
                    return when {
                        offset <= 1 -> offset
                        offset <= 4 -> offset - 1
                        offset <= 10 -> offset - 3
                        offset <= 15 -> offset - 4
                        else -> trimmed.length
                    }
                }
            }
        )
    }
}
