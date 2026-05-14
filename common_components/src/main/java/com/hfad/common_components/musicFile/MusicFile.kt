package com.hfad.common_components.musicFile

import android.R.attr.text
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hfad.common_components.R
import com.hfad.theme.DarkBlue
import com.hfad.theme.LitePurple
import com.hfad.theme.White
import com.hfad.theme.cyan
import com.hfad.theme.cyanCopy
import com.hfad.theme.purple

@Composable
fun MusicFile(
    text: String,
    time: String,
    onDelete: () -> Unit,
    onLongClick: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 6.dp,
                shape = RoundedCornerShape(16.dp),
                ambientColor = cyan.copy(alpha = 0.3f),
                spotColor = purple.copy(alpha = 0.2f)
            )
            .background(
                color = White,
                shape = RoundedCornerShape(16.dp)
            )
            .border(
                width = 1.dp,
                color = cyanCopy,
                shape = RoundedCornerShape(16.dp)
            )
            .combinedClickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(color = Color.Cyan.copy(alpha = 0.4f)),
                onClick = { },
                onLongClick = onLongClick
            )
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(44.dp)
                .background(
                    color = cyan.copy(alpha = 0.1f),
                    shape = RoundedCornerShape(12.dp)
                )
                .border(
                    width = 1.dp,
                    color = cyan.copy(alpha = 0.4f),
                    shape = RoundedCornerShape(12.dp)
                )
        ) {
            Icon(
                painter = painterResource(id = com.hfad.theme.R.drawable.outline_audio_file_24),
                contentDescription = null,
                tint = cyan,
                modifier = Modifier.size(22.dp)
            )
        }

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = text,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF1A1A1A),
                maxLines = 1
            )
            Text(
                text = time,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF9E9E9E)
            )
        }
    }
}
