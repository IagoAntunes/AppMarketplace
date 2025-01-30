package com.iagoaf.appmarketplace.src.auth.register.presentation

import android.text.InputType
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
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
import com.iagoaf.appmarketplace.core.ui.theme.orangeBase
import com.iagoaf.appmarketplace.core.ui.theme.shape
import com.iagoaf.appmarketplace.core.ui.theme.typography
import com.iagoaf.appmarketplace.src.auth.register.RegisterActions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    onAction: (RegisterActions) -> Unit = {},
) {

    val nameValue = remember { mutableStateOf("") }
    val phoneValue = remember { mutableStateOf("") }
    val mailValue = remember { mutableStateOf("") }
    val passwordValue = remember { mutableStateOf("") }
    val confirmPasswordValue = remember { mutableStateOf("") }
    val formValidated = remember { mutableStateOf(false) }


    fun validateForm() {
        formValidated.value = nameValue.value.isNotEmpty() &&
                phoneValue.value.isNotEmpty() &&
                mailValue.value.isNotEmpty() &&
                passwordValue.value.isNotEmpty() &&
                confirmPasswordValue.value.isNotEmpty()
    }

    Scaffold(
        modifier = modifier,
        containerColor = background,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            Image(
                painter = painterResource(R.drawable.ic_arrow_left),
                contentDescription = "Arrow left",
                colorFilter = ColorFilter.tint(gray500),
                modifier = Modifier
                    .padding(16.dp)
                    .clickable {
                        onAction(RegisterActions.GoBackToLogin)
                    }
            )
            Column(
                modifier = Modifier
                    .padding(start = 40.dp, end = 40.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier
                        .fillMaxWidth()
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
                        text = "Crie sua conta",
                        style = typography.titleLarge,
                        color = gray500,
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        text = "Informe os seus dados pessoais e de acesso",
                        style = typography.bodySmall,
                        color = gray300,
                    )
                }
                Spacer(Modifier.height(16.dp))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .size(120.dp)
                            .background(shape)
                            .align(Alignment.CenterHorizontally)
                            .clickable {
                                //
                            }
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_image_upload),
                            contentDescription = "ImageUpload",
                            colorFilter = ColorFilter.tint(orangeBase),
                            modifier = Modifier
                                .align(Alignment.Center)
                                .size(32.dp)
                        )
                    }
                    CTextField(
                        value = nameValue.value,
                        onValueChange = {
                            nameValue.value = it
                            validateForm()
                        },
                        label = "NOME",
                        leftIcon = R.drawable.ic_user,
                        hintText = "Seu nome completo",
                    )
                    CTextField(
                        value = phoneValue.value,
                        onValueChange = {
                            phoneValue.value = it
                            validateForm()
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Phone,
                        ),
                        label = "TELEFONE",
                        leftIcon = R.drawable.ic_call,
                        hintText = "(00) 00000-0000",
                    )
                }
                Spacer(Modifier.height(24.dp))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Acesso",
                        style = typography.titleSmall,
                    )
                    CTextField(
                        value = mailValue.value,
                        onValueChange = {
                            mailValue.value = it
                            validateForm()
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                        ),
                        label = "E-MAIL",
                        leftIcon = R.drawable.ic_mail,
                        hintText = "mail@examplo.br",
                    )
                    CTextField(
                        value = passwordValue.value,
                        type = CTextFieldType.PASSWORD,
                        onValueChange = {
                            passwordValue.value = it
                            validateForm()
                        },
                        label = "SENHA",
                        leftIcon = R.drawable.ic_access,
                        hintText = "Sua senha",
                    )
                    CTextField(
                        value = confirmPasswordValue.value,
                        type = CTextFieldType.PASSWORD,
                        onValueChange = {
                            confirmPasswordValue.value = it
                            validateForm()
                        },
                        label = "CONFIRMAR SENHA",
                        leftIcon = R.drawable.ic_access,
                        hintText = "Confirme a senha",
                    )
                    Spacer(Modifier.height(8.dp))
                    CButton(
                        type = CButtonSize.MEDIUM,
                        alignType = CButtonAlign.SPACE_BETWEEN,
                        text = "Cadastrar",
                        enabled = formValidated.value,
                        rightIcon = R.drawable.ic_arrow_right_02,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Spacer(Modifier.height(24.dp))
                Text(
                    text = "Já tem uma conta?",
                    style = typography.bodyMedium,
                    color = gray300,
                )
                Spacer(Modifier.height(8.dp))
                COutLinedButton(
                    type = COutlinedButtonSize.MEDIUM,
                    alignType = COutlinedButtonAlign.SPACE_BETWEEN,
                    text = "Acessar",
                    rightIcon = R.drawable.ic_arrow_right_02,
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        onAction(RegisterActions.GoBackToLogin)
                    }
                )
                Spacer(Modifier.height(16.dp))
            }
        }
    }
}

@Preview
@Composable
private fun RegisterScreenPreview() {
    RegisterScreen()
}