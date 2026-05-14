package com.hfad.common_components.file

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hfad.theme.White
import com.hfad.theme.cyan
import com.hfad.theme.purple

@Composable
fun CustomFile(
    title: String,
    onEditFile: () -> Unit,
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
                color = cyan.copy(alpha = 0.6f),
                shape = RoundedCornerShape(16.dp)
            )
            .combinedClickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(color = Color.Cyan.copy(alpha = 0.4f)),
                onClick = onEditFile,
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
                painter = painterResource(id = com.hfad.theme.R.drawable.file2),
                contentDescription = null,
                tint = cyan,
                modifier = Modifier.size(22.dp)
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF1A1A1A)
            )
        }
        Spacer(modifier = Modifier.weight(1f))

        Icon(
            painter = painterResource(id = com.hfad.theme.R.drawable.next),
            contentDescription = null,
            tint = cyan.copy(alpha = 0.7f),
            modifier = Modifier.size(16.dp)
        )
    }
}