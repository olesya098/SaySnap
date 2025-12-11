package com.hfad.common_components.topbar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hfad.theme.ColorButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit = { },
    navigationIcon: @Composable () -> Unit = { },
    actions: @Composable () -> Unit = { },
    ) {
    TopAppBar(
        modifier = modifier.fillMaxWidth().padding(horizontal = 16.dp),
        title = {
            title()
        },
        navigationIcon = {
            navigationIcon()
        },
        actions = {
            actions()
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = ColorButton
        )

    )
}