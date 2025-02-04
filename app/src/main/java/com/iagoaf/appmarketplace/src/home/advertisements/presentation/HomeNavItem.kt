package com.iagoaf.appmarketplace.src.home.advertisements.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iagoaf.appmarketplace.R
import com.iagoaf.appmarketplace.core.ui.components.COutlinedIconButton
import com.iagoaf.appmarketplace.core.ui.components.COutlinedIconButtonSize
import com.iagoaf.appmarketplace.core.ui.components.CTextField
import com.iagoaf.appmarketplace.core.ui.theme.AppMarketplaceTheme
import com.iagoaf.appmarketplace.core.ui.theme.background
import com.iagoaf.appmarketplace.core.ui.theme.gray400
import com.iagoaf.appmarketplace.core.ui.theme.gray500
import com.iagoaf.appmarketplace.core.ui.theme.orangeBase
import com.iagoaf.appmarketplace.core.ui.theme.typography
import com.iagoaf.appmarketplace.core.ui.theme.white

@Composable
fun HomeNavItem(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(background)
    ) {
        Column(
            Modifier
                .background(white)
                .padding(horizontal = 24.dp, vertical = 24.dp)

        ) {
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
            Spacer(Modifier.height(16.dp))
            Text(
                text = "Explore produtos",
                color = gray500,
                style = typography.bodySmall,
            )
            Spacer(Modifier.height(6.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Bottom
            ) {
                CTextField(
                    value = "",
                    leftIcon = R.drawable.ic_search,
                    onValueChange = {},
                    hintText = "Pesquisar",
                    modifier = Modifier.weight(1f)
                )
                Spacer(Modifier.width(8.dp))
                COutlinedIconButton(
                    type = COutlinedIconButtonSize.SMALL,
                    icon = R.drawable.ic_filter,
                    modifier = Modifier.size(48.dp),
                    onClick = {}
                )
            }
        }
        Spacer(Modifier.height(16.dp))
        LazyVerticalGrid(
            modifier = Modifier.padding(
                horizontal = 24.dp
            ),
            columns = GridCells.Adaptive(minSize = 150.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(5) {
                Box(
                    modifier = Modifier
                        .size(167.dp, 152.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(white)
                        .padding(6.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Image(
                            painter = painterResource(R.drawable.img_test),
                            contentDescription = "Product",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .fillMaxWidth()
                        )
                        Spacer(
                            Modifier
                                .height(6.dp)
                                .weight(1f)
                        )
                        Text(
                            text = "Sofá",
                            style = typography.bodySmall,
                            color = gray400
                        )
                        Text(
                            text = buildAnnotatedString {
                                withStyle(style = typography.bodySmall.toSpanStyle()) {
                                    append("R$ ")
                                }
                                withStyle(style = typography.titleMedium.toSpanStyle()) {
                                    append("1.200,90")
                                }
                            },
                            color = gray500
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun HomeNavItemPreview() {
    AppMarketplaceTheme {
        HomeNavItem()
    }
}