package com.hfad.antiplag_2_0.screens.home

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.painterResource
import com.hfad.common_components.menu.SideBarMenu
import com.hfad.common_components.scaffold.CustomScaffold
import com.hfad.theme.R
import kotlinx.coroutines.launch

@Composable
fun HomeScreen() {
    val scope = rememberCoroutineScope ()
    SideBarMenu { drawerState ->
        CustomScaffold (
            title = "HomeScreen",
            navigationIcon = {
                IconButton(
                    onClick = {
                        scope.launch {
                            drawerState.open()
                        }
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.menu),
                        contentDescription = null
                    )
                }
            }
        ){
        }

    }

}