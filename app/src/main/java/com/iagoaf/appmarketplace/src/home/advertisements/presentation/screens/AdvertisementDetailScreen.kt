package com.iagoaf.appmarketplace.src.home.advertisements.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBarDefaults.windowInsets
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.iagoaf.appmarketplace.R
import com.iagoaf.appmarketplace.core.ui.components.CButton
import com.iagoaf.appmarketplace.core.ui.components.CButtonSize
import com.iagoaf.appmarketplace.core.ui.theme.background
import com.iagoaf.appmarketplace.core.ui.theme.blueDark
import com.iagoaf.appmarketplace.core.ui.theme.blueLight
import com.iagoaf.appmarketplace.core.ui.theme.gray400
import com.iagoaf.appmarketplace.core.ui.theme.gray500
import com.iagoaf.appmarketplace.core.ui.theme.orangeBase
import com.iagoaf.appmarketplace.core.ui.theme.typography
import com.iagoaf.appmarketplace.core.ui.theme.white
import com.iagoaf.appmarketplace.src.home.advertisements.domain.models.AdvertisementModel
import com.iagoaf.appmarketplace.src.home.advertisements.presentation.actions.AdvertisementDetailActions

@Composable
fun AdvertisementDetailScreen(
    onAction: (AdvertisementDetailActions) -> Unit,
    advertisement: AdvertisementModel
) {
    Scaffold(
        containerColor = background,
        contentColor = background,
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .windowInsetsPadding(windowInsets)
                    .background(white)
                    .padding(24.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = typography.bodySmall.copy(
                                    fontWeight = FontWeight.Bold
                                )
                                    .toSpanStyle()
                            ) {
                                append("R$")
                            }
                            withStyle(style = typography.titleLarge.toSpanStyle()) {
                                append(advertisement.price.toString())
                            }
                        },
                        color = gray500
                    )
                    CButton(
                        type = CButtonSize.SMALL,
                        text = "Entrar em contato",
                        onClick = {
                            onAction(AdvertisementDetailActions.GoBackToHome)
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(start = 24.dp, end = 24.dp, top = 16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_arrow_left),
                    contentDescription = "Back",
                    colorFilter = ColorFilter.tint(orangeBase),
                    modifier = Modifier.clickable {
                        onAction(AdvertisementDetailActions.GoBackToHome)
                    }
                )
                Text(
                    text = "Voltar",
                    style = typography.bodySmall.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = orangeBase
                )
            }
            Spacer(Modifier.height(12.dp))
            AsyncImage(
                modifier = Modifier.fillMaxWidth(),
                model = advertisement.imageUrl,
                contentScale = ContentScale.FillWidth,
                contentDescription = advertisement.title,
            )
            Spacer(Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = advertisement.title,
                    style = typography.titleMedium,
                    color = gray400,
                )
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = typography.labelSmall.copy(fontWeight = FontWeight.Bold)
                                .toSpanStyle()
                        ) {
                            append("R$ ")
                        }
                        withStyle(style = typography.titleMedium.toSpanStyle()) {
                            append(advertisement.price.toString())
                        }
                    },
                    color = gray500
                )
            }
            Spacer(Modifier.height(16.dp))
            Text(
                text = advertisement.description,
                style = typography.bodySmall,
                color = gray400,
            )
            Spacer(Modifier.height(16.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Categoria",
                    style = typography.titleSmall,
                    color = gray500,
                )
                Text(
                    text = advertisement.category,
                    style = typography.bodySmall,
                    color = gray400,
                )
            }
            Spacer(Modifier.height(24.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(blueLight)
                    .padding(12.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(blueDark)
                            .padding(8.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_chart),
                            contentDescription = "Chart",
                            colorFilter = ColorFilter.tint(white)
                        )
                    }
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = typography.bodySmall.copy(
                                    fontWeight = FontWeight.Bold,
                                )
                                    .toSpanStyle()
                            ) {
                                append("${advertisement.countViewedProduct} pessoas ")
                            }
                            withStyle(style = typography.bodySmall.toSpanStyle()) {
                                append("visualizaram este produto")
                            }
                        },
                        color = gray400
                    )
                }
            }
        }
    }
}

