package com.hfad.antiplag_2_0.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hfad.common_components.menu.SideBarMenu
import com.hfad.common_components.scaffold.CustomScaffold
import com.hfad.theme.R
import kotlinx.coroutines.launch

@Composable
fun HomeScreen() {
    val scope = rememberCoroutineScope ()
    SideBarMenu { drawerState ->
        CustomScaffold (
            title = "Home Screen",
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
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ){
                Image(
                    painter = painterResource(id = R.drawable.load),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize().align(Alignment.Center)
                )
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.cloud),
                        contentDescription = null,
                        modifier = Modifier.size(253.dp)
                    )
                    Text(
                        text = "Upload A File",
                        color = Color.LightGray,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    )

                }

            }
        }

    }

}