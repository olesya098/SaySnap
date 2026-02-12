package com.hfad.common_components

import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hfad.theme.Black
import com.hfad.theme.gray

@Composable
fun FadedDivider(
    modifier: Modifier = Modifier,
    height: Dp = 1.dp,
    startEndAlpha: Float = 0.05f,
    centerAlpha: Float = 0.8f,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        gray.copy(alpha = startEndAlpha),
                        gray.copy(alpha = centerAlpha),
                        gray.copy(alpha = startEndAlpha)
                    ),
                )
            )
    )
}