package com.hfad.antiplag_2_0.screens.onboarding

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.content.MediaType.Companion.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role.Companion.Image

@Composable
fun OnboardingScreen(
    text: String,
    img: Image,
    title: String,
    background: Color,
    onClick: () -> Unit
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text = title,
            style = MaterialTheme.typography.bodySmall
        )

    }


}