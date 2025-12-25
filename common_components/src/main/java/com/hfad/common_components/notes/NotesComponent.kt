package com.hfad.common_components.notes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.hfad.common_components.R
import com.hfad.theme.LitePurple

@Composable
fun NotesComponent(
    text: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                LitePurple,
            )
            .clip(shape = RoundedCornerShape(13.dp))
            .padding(10.dp)
    ) {
        Image(
            painterResource(id = com.hfad.theme.R.drawable.file),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(10.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color.White
                ),
            contentAlignment = Alignment.CenterStart
        ){
            Text(
                text = text
            )

        }


    }
}