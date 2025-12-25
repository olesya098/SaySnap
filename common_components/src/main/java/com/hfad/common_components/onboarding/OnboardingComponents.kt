package com.hfad.common_components.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.hfad.common_components.navigation.Routes

@Composable
fun SplashComponents(
    image: Int,
    title: String,
    description: String,
    background: Color,
    dividerColor: Color,
    onNext: () -> Unit,
    currentIndex: Int,
    totalScreens: Int
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(background),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = Modifier
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelMedium,
                color = Color.Companion.White
            )
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier.size(180.dp)
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Companion.White,
                textAlign = TextAlign.Center
            )

        }
        HorizontalDivider(
            color = dividerColor,
            thickness = 1.dp
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    25.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "SKIP",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Companion.White,
                modifier = Modifier.clickable {
                    Routes.HOMESCREEN

                }
            )

            Spacer(modifier = Modifier.weight(1f))

            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(totalScreens) { index ->
                    Box(
                        modifier = Modifier
                            .size(if (index == currentIndex) 9.dp else 7.dp)
                            .background(
                                if (index == currentIndex) Color.White else dividerColor
                            )
                            .clip(shape = RoundedCornerShape(15.dp))
                    )
                }

            }

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                painter = painterResource(id = com.hfad.theme.R.drawable.next),
                contentDescription = null,
                modifier = Modifier
                    .size(19.dp)
                    .clickable { onNext() },
                tint = Color.Companion.White
            )

        }

    }

}

