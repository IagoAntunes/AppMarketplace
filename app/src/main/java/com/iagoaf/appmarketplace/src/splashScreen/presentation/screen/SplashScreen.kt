package com.iagoaf.appmarketplace.src.splashScreen.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iagoaf.appmarketplace.R
import com.iagoaf.appmarketplace.core.ui.theme.AppMarketplaceTheme
import com.iagoaf.appmarketplace.core.ui.theme.background
import com.iagoaf.appmarketplace.core.ui.theme.gray500
import com.iagoaf.appmarketplace.core.ui.theme.typography
import com.iagoaf.appmarketplace.src.splashScreen.presentation.action.SplashActions

@Composable
fun SplashScreen(
    onAction: (SplashActions) -> Unit,
    modifier: Modifier = Modifier
) {

    LaunchedEffect(Unit) {
        onAction(SplashActions.NavigateToLogin)
    }

    Scaffold(
        modifier = modifier,
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(background)
                .padding(innerPadding)
        ) {
            Image(
                painter = painterResource(R.drawable.marketplace_logo),
                contentDescription = "App Marketplace",
                modifier = Modifier.size(72.dp)
            )
            Text(
                text = "Marketplace",
                style = typography.titleLarge,
                color = gray500
            )
        }
    }
}

@Preview
@Composable
private fun SplashScreenPreview() {
    AppMarketplaceTheme {
        SplashScreen(
            onAction = {}
        )
    }
}