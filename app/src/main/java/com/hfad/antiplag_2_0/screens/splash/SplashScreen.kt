package com.hfad.antiplag_2_0.screens.splash

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun SplashScreen(onFinished: () -> Unit) {

    val infiniteTransition = rememberInfiniteTransition(label = "splash")

    val ring1Angle by infiniteTransition.animateFloat(
        initialValue = 0f, targetValue = 360f,
        animationSpec = infiniteRepeatable(tween(12000, easing = LinearEasing)),
        label = "ring1"
    )
    val ring2Angle by infiniteTransition.animateFloat(
        initialValue = 360f, targetValue = 0f,
        animationSpec = infiniteRepeatable(tween(20000, easing = LinearEasing)),
        label = "ring2"
    )

    val iconGlow by infiniteTransition.animateFloat(
        initialValue = 0f, targetValue = 1f,
        animationSpec = infiniteRepeatable(
            tween(2500, easing = FastOutSlowInEasing),
            RepeatMode.Reverse
        ),
        label = "glow"
    )

    val wavePhase by infiniteTransition.animateFloat(
        initialValue = 0f, targetValue = 2f * PI.toFloat(),
        animationSpec = infiniteRepeatable(tween(1200, easing = LinearEasing)),
        label = "wave"
    )

    val progress by infiniteTransition.animateFloat(
        initialValue = 0f, targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(2500, easing = FastOutSlowInEasing)),
        label = "progress"
    )

    val statusMessages = listOf(
        "Initializing...", "Loading models...",
        "Analyzing Audio...", "Transcribing...", "Almost ready..."
    )
    var statusIndex by remember { mutableStateOf(0) }
    LaunchedEffect(Unit) {
        while (true) {
            delay(1800)
            statusIndex = (statusIndex + 1) % statusMessages.size
        }
    }

    LaunchedEffect(Unit) {
        delay(5000)
        onFinished()
    }

    val deepBlue = Color(0xFF2A2D4A)
    val cyan = Color(0xFFAABFD8)
    val purple = Color(0xFF8B8FC4)
    val purpleBright = Color(0xFF9B8EC4)
    val blueBright = Color(0xFF6B7DB5)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.radialGradient(
                    colors = listOf(Color(0xFF4A5A8A), deepBlue),
                    radius = 1200f
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(blueBright.copy(alpha = 0.15f), Color.Transparent),
                    center = Offset(0f, 0f), radius = 400f
                ),
                radius = 400f, center = Offset(0f, 0f)
            )
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(purple.copy(alpha = 0.12f), Color.Transparent),
                    center = Offset(size.width, size.height * 0.3f), radius = 350f
                ),
                radius = 350f, center = Offset(size.width, size.height * 0.3f)
            )
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(cyan.copy(alpha = 0.08f), Color.Transparent),
                    center = Offset(0f, size.height * 0.7f), radius = 300f
                ),
                radius = 300f, center = Offset(0f, size.height * 0.7f)
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.size(320.dp)
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val strokeWidth = 1.dp.toPx()
                    val r1 = size.minDimension / 2
                    val r2 = size.minDimension / 2f - 40.dp.toPx()

                    drawCircle(
                        color = cyan.copy(alpha = 0.35f),
                        radius = r1, style = Stroke(strokeWidth)
                    )
                    val a1 = Math.toRadians(ring1Angle.toDouble())
                    val dotX1 = center.x + r1 * cos(a1).toFloat()
                    val dotY1 = center.y + r1 * sin(a1).toFloat()
                    drawCircle(color = cyan, radius = 5.dp.toPx(), center = Offset(dotX1, dotY1))
                    drawCircle(
                        color = cyan.copy(alpha = 0.4f),
                        radius = 10.dp.toPx(), center = Offset(dotX1, dotY1)
                    )

                    drawCircle(
                        color = purple.copy(alpha = 0.2f),
                        radius = r2, style = Stroke(strokeWidth)
                    )
                    val a2 = Math.toRadians(ring2Angle.toDouble())
                    val dotX2 = center.x + r2 * cos(a2).toFloat()
                    val dotY2 = center.y + r2 * sin(a2).toFloat()
                    drawCircle(color = purpleBright, radius = 4.dp.toPx(), center = Offset(dotX2, dotY2))
                    drawCircle(
                        color = purpleBright.copy(alpha = 0.3f),
                        radius = 8.dp.toPx(), center = Offset(dotX2, dotY2)
                    )

                    val barCount = 14
                    val barWidth = 3.dp.toPx()
                    val gap = 4.dp.toPx()
                    val iconRadius = 46.dp.toPx()
                    val waveOffset = 12.dp.toPx()

                    val leftEndX = center.x - iconRadius - waveOffset
                    val leftStartX = leftEndX - barCount * (barWidth + gap)

                    for (i in 0 until barCount) {
                        val barHeight = (sin(wavePhase + i * 0.5f) * 0.5f + 0.5f) * 50.dp.toPx() + 8.dp.toPx()
                        val x = leftStartX + i * (barWidth + gap)
                        drawRoundRect(
                            brush = Brush.verticalGradient(
                                colors = listOf(cyan, purple),
                                startY = center.y - barHeight / 2,
                                endY = center.y + barHeight / 2
                            ),
                            topLeft = Offset(x, center.y - barHeight / 2),
                            size = Size(barWidth, barHeight),
                            cornerRadius = CornerRadius(barWidth / 2)
                        )
                    }

                    val rightStartX = center.x + iconRadius + waveOffset

                    for (i in 0 until barCount) {
                        val barHeight = (sin(wavePhase + (barCount - i) * 0.5f) * 0.5f + 0.5f) * 50.dp.toPx() + 8.dp.toPx()
                        val x = rightStartX + i * (barWidth + gap)
                        drawRoundRect(
                            brush = Brush.verticalGradient(
                                colors = listOf(purpleBright, cyan),
                                startY = center.y - barHeight / 2,
                                endY = center.y + barHeight / 2
                            ),
                            topLeft = Offset(x, center.y - barHeight / 2),
                            size = Size(barWidth, barHeight),
                            cornerRadius = CornerRadius(barWidth / 2)
                        )
                    }
                }

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(72.dp)
                        .shadow(
                            elevation = (16 + iconGlow * 16).dp,
                            shape = RoundedCornerShape(20.dp),
                            ambientColor = cyan,
                            spotColor = cyan
                        )
                        .background(
                            Brush.linearGradient(
                                colors = listOf(Color(0xFF0D2080), Color(0xFF1A0060))
                            ),
                            RoundedCornerShape(20.dp)
                        )
                        .border(
                            1.dp,
                            cyan.copy(alpha = 0.3f + iconGlow * 0.3f),
                            RoundedCornerShape(20.dp)
                        )
                ) {
                    Icon(
                        painter = painterResource(id = com.hfad.theme.R.drawable.microphon),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(36.dp)
                    )
                }
            }

            Spacer(Modifier.height(28.dp))

            Text(
                buildAnnotatedString {
                    withStyle(SpanStyle(color = Color.White)) { append("Say") }
                    withStyle(
                        SpanStyle(
                            brush = Brush.linearGradient(listOf(cyan, purpleBright))
                        )
                    ) { append("Snap") }
                },
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.04.sp
            )

            Spacer(Modifier.height(6.dp))

            Text(
                "POWERED BY NEURAL NETWORKS",
                fontSize = 10.sp,
                letterSpacing = 0.15.em,
                color = Color.White.copy(alpha = 0.3f)
            )

            Spacer(Modifier.height(36.dp))

            AnimatedContent(
                targetState = statusMessages[statusIndex],
                transitionSpec = { fadeIn(tween(300)) togetherWith fadeOut(tween(300)) },
                label = "status"
            ) { text ->
                Text(
                    text,
                    fontSize = 13.sp,
                    color = cyan.copy(alpha = 0.7f),
                    fontFamily = FontFamily.Monospace
                )
            }

            Spacer(Modifier.height(14.dp))

            Box(
                modifier = Modifier
                    .width(180.dp)
                    .height(3.dp)
                    .clip(RoundedCornerShape(50))
                    .background(Color.White.copy(alpha = 0.08f))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(progress)
                        .clip(RoundedCornerShape(50))
                        .background(
                            Brush.linearGradient(listOf(blueBright, cyan, purpleBright))
                        )
                )
            }
        }
    }
}