package com.iagoaf.appmarketplace.core.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iagoaf.appmarketplace.R
import com.iagoaf.appmarketplace.core.ui.theme.orangeBase
import com.iagoaf.appmarketplace.core.ui.theme.white

enum class CIconButtonSize {
    SMALL,
    MEDIUM
}

@Composable
fun CIconButton(
    type: CIconButtonSize,
    @DrawableRes icon: Int,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(
            containerColor = orangeBase
        ),
        contentPadding = if (type == CIconButtonSize.SMALL) PaddingValues(10.dp) else PaddingValues(
            16.dp
        ),
        shape = RoundedCornerShape(10.dp),
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = "Icon",
            colorFilter = ColorFilter.tint(white)
        )
    }
}

@Preview
@Composable
private fun CIconButtonPreview() {
    CIconButton(
        type = CIconButtonSize.SMALL,
        icon = R.drawable.ic_mail,
    )
}