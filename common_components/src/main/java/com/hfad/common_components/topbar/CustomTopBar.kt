package com.hfad.common_components.topbar

import android.R.attr.end
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.hfad.theme.LitePurple
import com.hfad.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit = { },
    navigationIcon: @Composable () -> Unit = { },
    actions: @Composable () -> Unit = { },
) {
    TopAppBar(
        modifier = modifier.fillMaxWidth(),
        title = {},
        navigationIcon = {
            Box(
                modifier = Modifier
                    .offset(x = (-16).dp)
            ) {
                Row(
                    modifier = Modifier
                        .clip(
                            shape = RoundedCornerShape(
                                topStart = 0.dp,
                                bottomStart = 0.dp,
                                topEnd = 13.dp,
                                bottomEnd = 13.dp
                            )
                        )
                        .background(
                            White
                        )

                        .padding(
                            start = 16.dp,
                            end = 35.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    navigationIcon()
                    title()

                }
            }
        },
        actions = {
            actions()
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = LitePurple
        )

    )
}