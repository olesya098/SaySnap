package com.hfad.common_components.scaffold

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.hfad.common_components.topbar.CustomTopBar
import com.hfad.theme.White

@Composable
fun CustomScaffold(
    title: String = "",
    actions: @Composable () -> Unit = {},
    navigationIcon: @Composable () -> Unit = { },
    action: @Composable () -> Unit,
) {
    Scaffold(
        topBar = {
            CustomTopBar(
                title = {
                    Text(
                        text = title,
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                },
                actions = { actions() },
                navigationIcon = { navigationIcon() }

            )
        },
        containerColor = White
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding)
        ){
            action()
        }


    }

}