package com.hfad.common_components.notesForm

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.hfad.common_components.R
import com.hfad.theme.LitePurple
import com.hfad.theme.White

@Composable
fun NotesFormComponents(
    text: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(
                shape = RoundedCornerShape(17.dp)
            )
            .background(LitePurple)
    ) {
        Image(
            painter = painterResource(id = com.hfad.theme.R.drawable.file2),
            contentDescription = null,
            modifier = Modifier
                .size(27.dp)
                .padding(10.dp)
        )
        Box(
            modifier = Modifier
                .background(
                    White,
                    shape = RoundedCornerShape(13.dp)
                )
                .fillMaxWidth()

        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium
            )
        }

    }
}