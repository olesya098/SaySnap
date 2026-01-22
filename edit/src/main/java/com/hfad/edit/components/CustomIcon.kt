package com.hfad.edit.components

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.hfad.theme.R

@Composable
fun CustomIcon(
    imageId: Int,
    onClick: () -> Unit
) {
    Image(
        painter = painterResource(id = imageId),
        contentDescription = null,
        modifier = Modifier.size(17.dp).clickable{
            onClick()
        }
    )
}