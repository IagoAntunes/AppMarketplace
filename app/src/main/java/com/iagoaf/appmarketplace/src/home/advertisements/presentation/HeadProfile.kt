package com.iagoaf.appmarketplace.src.home.advertisements.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iagoaf.appmarketplace.R
import com.iagoaf.appmarketplace.core.ui.theme.gray500
import com.iagoaf.appmarketplace.core.ui.theme.orangeBase
import com.iagoaf.appmarketplace.core.ui.theme.typography

@Composable
fun HomeHead(modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(gray500)
        )
        Spacer(Modifier.width(16.dp))
        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = "Olá, Brandon!",
                color = gray500,
                style = typography.titleSmall,
            )
            Row {
                Text(
                    text = "Ver perfil",
                    color = orangeBase,
                    style = typography.bodySmall,
                )
                Spacer(Modifier.width(4.dp))
                Image(
                    painter = painterResource(R.drawable.ic_arrow_right_02),
                    contentDescription = "Arrow Right",
                    colorFilter = ColorFilter.tint(orangeBase),
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun HomeHeadPreview() {
    HomeHead()
}