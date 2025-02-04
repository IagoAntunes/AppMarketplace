package com.iagoaf.appmarketplace.src.home.profile.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iagoaf.appmarketplace.R
import com.iagoaf.appmarketplace.core.ui.components.COutlinedIconButton
import com.iagoaf.appmarketplace.core.ui.components.COutlinedIconButtonSize
import com.iagoaf.appmarketplace.core.ui.components.CTextField
import com.iagoaf.appmarketplace.core.ui.components.CTextFieldType
import com.iagoaf.appmarketplace.core.ui.theme.background
import com.iagoaf.appmarketplace.src.home.profile.presentation.actions.ProfileActions
import com.iagoaf.appmarketplace.src.home.profile.presentation.state.ProfileNavItemState

@Composable
fun ProfileNavItem(
    modifier: Modifier = Modifier,
    onAction: (ProfileActions) -> Unit,
    state: ProfileNavItemState,
) {

    LaunchedEffect(Unit) {
        onAction(ProfileActions.LoadUserInfo)
    }

    Scaffold(
        containerColor = background,
        contentColor = background,
        modifier = modifier,
    ) { innerPadding ->
        when (state) {
            is ProfileNavItemState.Error -> {}
            is ProfileNavItemState.Idle -> {}
            is ProfileNavItemState.Loading -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }

            is ProfileNavItemState.Success -> {
                val user = state.user
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .padding(40.dp)
                ) {
                    Row {
                        Spacer(Modifier.weight(1f))
                        COutlinedIconButton(
                            type = COutlinedIconButtonSize.SMALL,
                            icon = R.drawable.ic_logout,
                            onClick = {
                                onAction(ProfileActions.Logout)
                            }
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    CTextField(
                        value = user.name,
                        onValueChange = {},
                        enabled = false,
                        label = "NOME",
                        leftIcon = R.drawable.ic_user,
                        hintText = "Seu nome completo",
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    CTextField(
                        value = user.phone,
                        onValueChange = {},
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Phone,
                        ),
                        type = CTextFieldType.PHONE,
                        enabled = false,
                        label = "TELEFONE",
                        leftIcon = R.drawable.ic_call,
                        hintText = "(00) 00000-0000",
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    CTextField(
                        value = user.mail,
                        onValueChange = {},
                        enabled = false,
                        hintText = "mail@exemplo.br",
                        label = "E-MAIL",
                        leftIcon = R.drawable.ic_mail
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    CTextField(
                        value = "*********",
                        onValueChange = {},
                        enabled = false,
                        hintText = "Sua senha",
                        label = "SENHA ATUAL",
                        leftIcon = R.drawable.ic_access
                    )
                }
            }

            is ProfileNavItemState.SuccessLogout -> {}
        }
    }
}

@Preview
@Composable
private fun ProfileNavItemPreview() {
    ProfileNavItem(
        onAction = {},
        state = ProfileNavItemState.Idle
    )
}