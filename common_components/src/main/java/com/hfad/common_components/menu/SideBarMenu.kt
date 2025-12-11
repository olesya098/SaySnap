package com.hfad.common_components.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.hfad.common_components.navigation.LocalNavigator
import com.hfad.common_components.navigation.Routes
import com.hfad.theme.Background
import com.hfad.theme.R
import kotlinx.coroutines.launch

@Composable
fun SideBarMenu(
    action: @Composable (DrawerState) -> Unit,

    ) {
    val scope = rememberCoroutineScope()
    val interactionsSource = remember { MutableInteractionSource() }
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    val configuration = LocalConfiguration.current
    val width = (configuration.screenWidthDp * 0.8).dp

    val navigator = LocalNavigator.current
    val routes = listOf(
        SideBarModel(
            title = "Главная",
            imageId = R.drawable.home_unfill,
            route = Routes.HOMESCREEN
        ),
        )
    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = false,
        drawerContent = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent)
                    .clickable(
                        interactionSource = interactionsSource,
                        indication = null
                    ){
                        scope.launch {
                            drawerState.close()
                        }
                    },
                contentAlignment = Alignment.CenterStart
            ){
                Column (
                    modifier = Modifier
                        .fillMaxHeight()
                        .background(Background)
                        .width(width)
                ){

                    routes.forEach {
                        Row (
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable{
                                   navigator.navigate(it.route)
                                }
                        ){
                            Icon(
                                painter = painterResource(it.imageId),
                                contentDescription = null
                            )
                            Text(
                                text = it.title
                            )
                        }
                    }
                }
            }
        }
    ) {
        action(
            drawerState
        )
    }
}