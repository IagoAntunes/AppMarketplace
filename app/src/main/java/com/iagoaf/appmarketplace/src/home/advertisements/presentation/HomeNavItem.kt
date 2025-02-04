package com.iagoaf.appmarketplace.src.home.advertisements.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.iagoaf.appmarketplace.core.ui.theme.background

@Composable
fun HomeNavItem(
    modifier: Modifier = Modifier,
    advertisementsViewModel: AdvertisementsViewModel,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(background)
            .padding(horizontal = 24.dp, vertical = 24.dp)
    ) {

        HomeHead()
        Spacer(Modifier.height(16.dp))
        ListAdvertisements(
            state = advertisementsViewModel.state.collectAsState().value,
            onAction = advertisementsViewModel::onAction
        )
    }
}

