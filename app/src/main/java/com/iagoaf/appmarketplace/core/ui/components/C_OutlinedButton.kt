package com.iagoaf.appmarketplace.core.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
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

enum class COutlinedButtonSize {
    SMALL,
    MEDIUM
}

enum class COutlinedButtonAlign {
    CENTER,
    SPACE_BETWEEN
}

@Composable
fun COutLinedButton(
    type: COutlinedButtonSize,
    alignType: COutlinedButtonAlign = COutlinedButtonAlign.CENTER,
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    @DrawableRes leftIcon: Int? = null,
    @DrawableRes rightIcon: Int? = null,
) {
    OutlinedButton(
        onClick = onClick,
        border = BorderStroke(
            width = 1.dp,
            color = orangeBase
        ),
        shape = RoundedCornerShape(10.dp),
        contentPadding = if (type == COutlinedButtonSize.SMALL) PaddingValues(
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
            modifier = if (alignType == COutlinedButtonAlign.SPACE_BETWEEN) {
                Modifier.fillMaxWidth()
            } else {
                Modifier
            }
        ) {
            if (leftIcon != null) {
                Image(
                    painter = painterResource(leftIcon),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(orangeBase)
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            }
            Text(
                text = text,
                fontSize = 16.sp,
                color = orangeBase,
            )
            if (rightIcon != null) {
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Image(
                    painter = painterResource(rightIcon),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(orangeBase)
                )
            }
        }
    }
}

private fun defineButtonHorizontalAlignment(alignType: COutlinedButtonAlign): Arrangement.Horizontal {
    return when (alignType) {
        COutlinedButtonAlign.CENTER -> Arrangement.Center
        COutlinedButtonAlign.SPACE_BETWEEN -> Arrangement.SpaceBetween
    }
}


@Preview
@Composable
private fun CButtonMediumPreview() {
    COutLinedButton(
        type = COutlinedButtonSize.MEDIUM,
        leftIcon = R.drawable.ic_mail,
        rightIcon = R.drawable.ic_mail,
        text = "Placeholder",
        onClick = {}
    )
}

@Preview
@Composable
private fun CButtonSmallPreview() {
    COutLinedButton(
        type = COutlinedButtonSize.SMALL,
        leftIcon = R.drawable.ic_mail,
        rightIcon = R.drawable.ic_mail,
        text = "Placeholder",
        onClick = {}
    )
}