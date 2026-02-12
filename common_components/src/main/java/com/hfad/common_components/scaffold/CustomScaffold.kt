package com.hfad.common_components.scaffold

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hfad.common_components.FadedDivider
import com.hfad.theme.R
import com.hfad.theme.background

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomScaffold(
    title: String = "",
    actions: @Composable () -> Unit = {},
    navigationIcon: @Composable () -> Unit = { },
    action: @Composable () -> Unit,
) {
    Scaffold(
        topBar = {
            Column {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = title,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1A1A1A)
                        )
                    },
                    actions = { actions() },
                    navigationIcon = { navigationIcon() },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = background
                    )
                )
                FadedDivider(
                    modifier = Modifier.padding(horizontal = 26.dp).padding(top = 6.dp).padding(bottom = 10.dp),
                    height = 0.8.dp,
                    startEndAlpha = 0.2f,
                    centerAlpha = 1f
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding)
        ) {
            action()
        }
    }

}