package com.iagoaf.appmarketplace.core.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iagoaf.appmarketplace.R
import com.iagoaf.appmarketplace.core.ui.theme.orangeBase
import com.iagoaf.appmarketplace.core.ui.theme.white

enum class COutlinedIconButtonSize {
    SMALL,
    MEDIUM
}

@Composable
fun COutlinedIconButton(
    type: COutlinedIconButtonSize,
    @DrawableRes icon: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .border(
                BorderStroke(1.dp, orangeBase),
                shape = RoundedCornerShape(10.dp)
            )
            .padding(
                if (type == COutlinedIconButtonSize.SMALL) PaddingValues(
                    10.dp,
                ) else PaddingValues(
                    16.dp
                )
            )
            .clickable {
                onClick()
            }
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = "Icon",
            colorFilter = ColorFilter.tint(orangeBase),
            modifier = Modifier.align(Alignment.Center)
        )
    }
    /*
        Box(
        modifier = modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(10.dp))
            .border(
                BorderStroke(1.dp, orangeBase),
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = "Icon",
            colorFilter = ColorFilter.tint(orangeBase),
            modifier = Modifier
                .size(20.dp)
                .align(Alignment.Center)
        )
    }
     */
}

@Preview
@Composable
private fun CIconButtonPreview() {
    COutlinedIconButton(
        type = COutlinedIconButtonSize.SMALL,
        icon = R.drawable.ic_mail,
        onClick = {}
    )
}