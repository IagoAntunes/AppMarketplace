package com.iagoaf.appmarketplace.src.auth.login.presentation.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iagoaf.appmarketplace.R
import com.iagoaf.appmarketplace.core.ui.components.CButton
import com.iagoaf.appmarketplace.core.ui.components.CButtonAlign
import com.iagoaf.appmarketplace.core.ui.components.CButtonSize
import com.iagoaf.appmarketplace.core.ui.components.COutLinedButton
import com.iagoaf.appmarketplace.core.ui.components.COutlinedButtonAlign
import com.iagoaf.appmarketplace.core.ui.components.COutlinedButtonSize
import com.iagoaf.appmarketplace.core.ui.components.CTextField
import com.iagoaf.appmarketplace.core.ui.components.CTextFieldType
import com.iagoaf.appmarketplace.core.ui.theme.background
import com.iagoaf.appmarketplace.core.ui.theme.gray300
import com.iagoaf.appmarketplace.core.ui.theme.gray500
import com.iagoaf.appmarketplace.core.ui.theme.typography
import com.iagoaf.appmarketplace.src.auth.login.presentation.LoginActions
import com.iagoaf.appmarketplace.src.auth.login.presentation.LoginScreenListener
import com.iagoaf.appmarketplace.src.auth.login.presentation.LoginScreenState
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onAction: (LoginActions) -> Unit = {},
    state: LoginScreenState,
    listener: LoginScreenListener,
) {
    val emailValue = remember {
        mutableStateOf("")
    }
    val passwordValue = remember {
        mutableStateOf("")
    }

    val formValidated = remember {
        mutableStateOf(false)
    }

    fun validateForm() {
        formValidated.value = emailValue.value.isNotEmpty() && passwordValue.value.isNotEmpty()
    }

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(listener) {
        Log.e("LoginScreen", "Listener: $listener")
        when (listener) {
            is LoginScreenListener.Error -> {
                scope.launch {
                    snackbarHostState.showSnackbar(
                        message = listener.errorMessage,
                        duration = SnackbarDuration.Short
                    )
                }
            }

            LoginScreenListener.Idle -> {

            }

            is LoginScreenListener.Success -> {
                scope.launch {
                    snackbarHostState.showSnackbar(
                        message = "SUCCESS",
                        duration = SnackbarDuration.Short
                    )
                }

                onAction(LoginActions.NavigateToHome)
            }
        }
    }

    Scaffold(
        containerColor = background,
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(vertical = 64.dp, horizontal = 40.dp)
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f, fill = true)
            ) {
                Image(
                    painter = painterResource(R.drawable.marketplace_logo),
                    contentDescription = "Marketplace logo",
                    modifier = Modifier
                        .size(64.dp)
                        .align(
                            Alignment.CenterHorizontally
                        )
                )
                Spacer(Modifier.height(16.dp))
                Text(
                    text = "Acesse sua conta",
                    style = typography.titleLarge,
                    color = gray500,
                )
                Text(
                    text = "Informe seu e-mail e senha para entrar",
                    style = typography.bodySmall,
                    color = gray300,
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.weight(2f, fill = true)
            ) {
                CTextField(
                    value = emailValue.value,
                    onValueChange = {
                        emailValue.value = it
                        validateForm()
                    },
                    hintText = "mail@exemplo.br",
                    label = "E-MAIL",
                    leftIcon = R.drawable.ic_mail
                )
                Spacer(modifier = Modifier.height(8.dp))
                CTextField(
                    value = passwordValue.value,
                    type = CTextFieldType.PASSWORD,
                    onValueChange = {
                        passwordValue.value = it
                        validateForm()
                    },
                    hintText = "Sua senha",
                    label = "SENHA",
                    leftIcon = R.drawable.ic_access
                )
                Spacer(modifier = Modifier.height(24.dp))
                CButton(
                    type = CButtonSize.MEDIUM,
                    alignType = CButtonAlign.SPACE_BETWEEN,
                    text = "Acessar",
                    rightIcon = R.drawable.ic_arrow_right_02,
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        onAction(LoginActions.Login(emailValue.value, passwordValue.value))
                    }
                )
            }
            Column(
                modifier = Modifier.weight(1f, fill = true),
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    text = "Ainda não tem uma conta?",
                    style = typography.bodyMedium,
                    color = gray300,
                )
                Spacer(Modifier.height(12.dp))
                COutLinedButton(
                    type = COutlinedButtonSize.MEDIUM,
                    alignType = COutlinedButtonAlign.SPACE_BETWEEN,
                    text = "Cadastrar",
                    rightIcon = R.drawable.ic_arrow_right_02,
                    onClick = {
                        onAction(LoginActions.NavigateToRegister)
                    }
                )
            }
        }
    }
}


@Preview
@Composable
private fun LoginScreenPreview() {
    LoginScreen(
        state = LoginScreenState.Idle,
        listener = LoginScreenListener.Idle
    )
}