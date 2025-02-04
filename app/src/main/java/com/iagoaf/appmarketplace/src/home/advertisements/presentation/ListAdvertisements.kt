package com.iagoaf.appmarketplace.src.home.advertisements.presentation

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
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.iagoaf.appmarketplace.R
import com.iagoaf.appmarketplace.core.ui.components.COutlinedIconButton
import com.iagoaf.appmarketplace.core.ui.components.COutlinedIconButtonSize
import com.iagoaf.appmarketplace.core.ui.components.CTextField
import com.iagoaf.appmarketplace.core.ui.theme.gray400
import com.iagoaf.appmarketplace.core.ui.theme.gray500
import com.iagoaf.appmarketplace.core.ui.theme.typography
import com.iagoaf.appmarketplace.core.ui.theme.white
import com.iagoaf.appmarketplace.src.home.advertisements.presentation.actions.ListAdvertisementsActions
import com.iagoaf.appmarketplace.src.home.advertisements.presentation.state.ListAdvertisementsState

@Composable
fun ListAdvertisements(
    state: ListAdvertisementsState,
    onAction: (ListAdvertisementsActions) -> Unit
) {
    val filterValue = remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        onAction(ListAdvertisementsActions.GetAll)
    }


    Column {
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
                value = filterValue.value,
                leftIcon = R.drawable.ic_search,
                onValueChange = {},
                enabled = state is ListAdvertisementsState.Success,
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
    when (state) {
        is ListAdvertisementsState.Error -> {}
        ListAdvertisementsState.Idle -> {}
        ListAdvertisementsState.Loading -> CircularProgressIndicator()
        is ListAdvertisementsState.Success -> {
            LazyVerticalGrid(
                modifier = Modifier.padding(
                    horizontal = 24.dp
                ),
                columns = GridCells.Adaptive(minSize = 150.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(state.advertisements) { advertisement ->
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
                            AsyncImage(
                                model = advertisement.imageUrl,
                                contentDescription = "Product",
                                contentScale = ContentScale.FillWidth,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .fillMaxWidth()
                                    .weight(1f)
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = advertisement.title,
                                style = typography.bodySmall,
                                color = gray400
                            )
                            Text(
                                text = buildAnnotatedString {
                                    withStyle(style = typography.bodySmall.toSpanStyle()) {
                                        append("R$ ")
                                    }
                                    withStyle(style = typography.titleMedium.toSpanStyle()) {
                                        append(advertisement.price.toString())
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
}

@Preview
@Composable
private fun ListAdvertisementsPreview() {
    ListAdvertisements(
        state = ListAdvertisementsState.Idle,
        onAction = {}
    )
}
