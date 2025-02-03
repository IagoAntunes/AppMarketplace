package com.iagoaf.appmarketplace.core.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iagoaf.appmarketplace.R
import com.iagoaf.appmarketplace.core.ui.theme.AppMarketplaceTheme
import com.iagoaf.appmarketplace.core.ui.theme.gray100
import com.iagoaf.appmarketplace.core.ui.theme.gray200
import com.iagoaf.appmarketplace.core.ui.theme.gray300
import com.iagoaf.appmarketplace.core.ui.theme.orangeBase
import com.iagoaf.appmarketplace.core.ui.theme.typography
import com.iagoaf.appmarketplace.core.util.PhoneNumberVisualTransformation

enum class CTextFieldType {
    IDLE,
    PASSWORD,
    PHONE,
}

@Composable
fun CTextField(
    modifier: Modifier = Modifier,
    type: CTextFieldType = CTextFieldType.IDLE,
    value: String,
    label: String? = null,
    errorMessage: String? = null,
    onValueChange: (String) -> Unit,
    hintText: String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    enabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    leftIcon: Int? = null,
    rightIcon: Int? = null,
    singleLine: Boolean = true,
) {
    val isObscure = remember { mutableStateOf(true) }
    val isFocused = remember { mutableStateOf(false) }
    val hasEnteredText = remember { mutableStateOf(false) }

    val textFieldContent: @Composable () -> Unit = {
        OutlinedTextField(
            value = value,
            singleLine = singleLine,
            enabled = enabled,
            onValueChange = {
                onValueChange(it)
                hasEnteredText.value = it.isNotEmpty()
            },
            textStyle = typography.bodyMedium.copy(
                color = gray300,
                textAlign = TextAlign.Start
            ),
            placeholder = {
                Text(
                    text = hintText,
                    style = typography.bodyMedium,
                    color = gray200
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = Color.Transparent
            ),
            keyboardOptions = if (type == CTextFieldType.PASSWORD) {
                KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password)
            } else {
                keyboardOptions
            },
            prefix = {
                if (leftIcon != null) {
                    Image(
                        painter = painterResource(id = leftIcon),
                        contentDescription = "",
                        colorFilter = if (isFocused.value || hasEnteredText.value)
                            ColorFilter.tint(orangeBase)
                        else
                            ColorFilter.tint(gray200),
                        modifier = Modifier.padding(end = 6.dp)
                    )
                }
            },
            suffix = {
                if (type == CTextFieldType.PASSWORD) {
                    Spacer(Modifier.width(6.dp))
                    Image(
                        painter = painterResource(
                            id = if (isObscure.value) R.drawable.ic_view else R.drawable.ic_view_off
                        ),
                        contentDescription = "",
                        modifier = Modifier.clickable {
                            isObscure.value = !isObscure.value
                        },
                        colorFilter = ColorFilter.tint(gray300)
                    )
                } else {
                    if (rightIcon != null) {
                        Image(
                            painter = painterResource(id = rightIcon),
                            contentDescription = "",
                            colorFilter = ColorFilter.tint(gray200)
                        )
                    }
                }
            },
            visualTransformation = defineVisualTransformation(type, isObscure.value),
            modifier = modifier
                .fillMaxWidth()
                .onFocusChanged { focusState ->
                    isFocused.value = focusState.isFocused
                }
                .drawBehind {
                    val strokeWidth = 1.dp.toPx()
                    val y = size.height - strokeWidth / 2
                    drawLine(
                        color = gray100,
                        start = Offset(0f, y),
                        end = Offset(size.width, y),
                        strokeWidth = strokeWidth
                    )
                }
        )
    }

    if (label != null || errorMessage != null) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            if (label != null) {
                Text(
                    text = label,
                    style = typography.labelMedium,
                    color = if (isFocused.value) orangeBase else gray300
                )
            }
            textFieldContent()
            if (errorMessage != null) {
                Text(
                    text = errorMessage,
                    style = typography.bodySmall,
                    color = Color.Red,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    } else {
        // Caso contrário, renderiza somente o OutlinedTextField.x
        textFieldContent()
    }
}

fun defineVisualTransformation(type: CTextFieldType, isObscure: Boolean): VisualTransformation {
    return when (type) {
        CTextFieldType.PASSWORD -> {
            if (isObscure) PasswordVisualTransformation() else VisualTransformation.None
        }

        CTextFieldType.PHONE -> PhoneNumberVisualTransformation()
        else -> VisualTransformation.None
    }
}

@Preview
@Composable
fun CTextFieldPreview() {
    AppMarketplaceTheme {
        CTextField(
            value = "",
            onValueChange = {},
            hintText = "Digite algo",
            leftIcon = R.drawable.ic_mail,
            rightIcon = R.drawable.ic_mail,
        )
    }
}

@Preview
@Composable
fun CTextFieldErrorStatePreview() {
    AppMarketplaceTheme {
        CTextField(
            value = "",
            onValueChange = {},
            hintText = "Digite algo",
            leftIcon = R.drawable.ic_mail,
            rightIcon = R.drawable.ic_mail,
            errorMessage = "Erro de senha"
        )
    }
}

@Preview
@Composable
fun CTextFieldPasswordPreview() {
    val emailValue = remember { mutableStateOf("") }
    AppMarketplaceTheme {
        CTextField(
            type = CTextFieldType.PASSWORD,
            value = emailValue.value,
            onValueChange = { emailValue.value = it },
            hintText = "Digite algo"
        )
    }
}
