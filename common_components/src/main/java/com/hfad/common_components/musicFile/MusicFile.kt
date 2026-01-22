package com.hfad.common_components.musicFile

import android.R.attr.text
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.hfad.common_components.R
import com.hfad.theme.DarkBlue
import com.hfad.theme.LitePurple
import com.hfad.theme.White

@Composable
fun MusicFile(
    text: String,
    time: String,
    onDelete: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
                .clip(shape = RoundedCornerShape(17.dp))
                .background(LitePurple)
                .padding(15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = com.hfad.theme.R.drawable.music),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .padding(end = 15.dp)
            )

            Column(
                modifier = Modifier
                    .weight(1f),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(13.dp))
                        .background(
                            White
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp)

                ) {
                    Text(
                        text = text,
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier.padding(5.dp)

                    )
                }
                Box(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(13.dp))
                        .background(
                            White
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp)

                ) {
                    Text(
                        text = time,
                        style = MaterialTheme.typography.labelLarge,
                    )
                }
            }


        }
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 8.dp, end = 8.dp)
                .clip(shape = CircleShape)
                .background(White)
                .size(24.dp)
                .border(
                    width = 0.5.dp,
                    color = DarkBlue,
                    shape = CircleShape
                )
                .clickable { onDelete() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = com.hfad.theme.R.drawable.close),
                contentDescription = null,
                modifier = Modifier
                    .size(8.dp),
                tint = DarkBlue
            )
        }
    }

}