package com.hfad.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.league_spartan_regular)),
        fontSize = 14.sp,
        color = Black
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
    labelMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.league_spartan_medium)),
        fontSize = 20.sp,
        color = Black
    ),
    labelLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.league_spartan_medium)),
        fontSize = 14.sp,
        color = DarkBlue
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.league_spartan_regular)),
        fontSize = 14.sp,
        color = DarkBlue
    )

)