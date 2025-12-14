package com.hfad.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.league_spartan_regular)),
        fontSize = 14.sp,
        color = Color.Black
    ),
    bodySmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.league_spartan_light)),
        fontSize = 20.sp,
        color = Black
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.league_spartan_medium)),
        fontSize = 14.sp,
        color = Black
    ),
)