package com.iagoaf.appmarketplace.core.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.iagoaf.appmarketplace.R
import com.iagoaf.appmarketplace.core.ui.theme.orangeBase
import com.iagoaf.appmarketplace.core.ui.theme.white

enum class CButtonSize {
    SMALL,
    MEDIUM
}

enum class CButtonAlign {
    CENTER,
    SPACE_BETWEEN
}


@Composable
fun CButton(
    type: CButtonSize,
    alignType: CButtonAlign = CButtonAlign.CENTER,
    text: String,
    modifier: Modifier = Modifier,
    @DrawableRes leftIcon: Int? = null,
    @DrawableRes rightIcon: Int? = null,
) {
    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(
            containerColor = orangeBase
        ),
        shape = RoundedCornerShape(10.dp),
        contentPadding = if (type == CButtonSize.SMALL) PaddingValues(
            horizontal = 16.dp,
            vertical = 6.dp
        ) else PaddingValues(
            horizontal = 24.dp,
            vertical = 12.dp
        ),
        modifier = modifier,
    ) {
        Row(
            horizontalArrangement = defineButtonHorizontalAlignment(alignType),
            verticalAlignment = Alignment.CenterVertically,
            modifier = if (alignType == CButtonAlign.SPACE_BETWEEN) {
                Modifier.fillMaxWidth()
            } else {
                Modifier
            }
        ) {
            if (leftIcon != null) {
                Image(
                    painter = painterResource(leftIcon),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(white)
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            }
            Text(
                text = text,
                fontSize = 16.sp,
                color = white,
            )
            if (rightIcon != null) {
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Image(
                    painter = painterResource(rightIcon),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(white)
                )
            }
        }

    }


}

private fun defineButtonHorizontalAlignment(alignType: CButtonAlign): Arrangement.Horizontal {
    return when (alignType) {
        CButtonAlign.CENTER -> Arrangement.Center
        CButtonAlign.SPACE_BETWEEN -> Arrangement.SpaceBetween
    }
}

@Preview
@Composable
private fun CButtonMediumPreview() {
    CButton(
        type = CButtonSize.MEDIUM,
        leftIcon = R.drawable.ic_mail,
        rightIcon = R.drawable.ic_mail,
        text = "Placeholder"
    )
}

@Preview
@Composable
private fun CButtonSmallPreview() {
    CButton(
        type = CButtonSize.SMALL,
        leftIcon = R.drawable.ic_mail,
        rightIcon = R.drawable.ic_mail,
        text = "Placeholder"
    )
}