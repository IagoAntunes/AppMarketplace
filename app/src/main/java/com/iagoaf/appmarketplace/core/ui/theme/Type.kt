package com.iagoaf.appmarketplace.core.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.iagoaf.appmarketplace.R

val dmSansFontFamily = FontFamily(
    Font(R.font.dm_sans_medium, FontWeight.Normal),
    Font(R.font.dm_sans_medium, FontWeight.Medium),
    Font(R.font.dm_sans_semi_bold, FontWeight.SemiBold),
    Font(R.font.dm_sans_bold, FontWeight.Bold),
)

val poppinsFontFamily = FontFamily(
    Font(R.font.poppins_medium, FontWeight.Normal),
    Font(R.font.poppins_medium, FontWeight.Medium),
    Font(R.font.poppins_semibold, FontWeight.SemiBold),
    Font(R.font.poppins_bold, FontWeight.Bold),
)

private const val activatePreview = true

val typography = Typography(
    titleLarge = TextStyle(
        fontFamily = if (activatePreview) FontFamily.Default else dmSansFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
    titleMedium = TextStyle(
        fontFamily = if (activatePreview) FontFamily.Default else dmSansFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
    ),
    titleSmall = TextStyle(
        fontFamily = if (activatePreview) FontFamily.Default else dmSansFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = if (activatePreview) FontFamily.Default else poppinsFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = if (activatePreview) FontFamily.Default else poppinsFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
    ),
    labelMedium = TextStyle(
        fontFamily = if (activatePreview) FontFamily.Default else poppinsFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
    ),
    labelSmall = TextStyle(
        fontFamily = if (activatePreview) FontFamily.Default else poppinsFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
    ),
)