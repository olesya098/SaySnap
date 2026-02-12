package com.hfad.antiplag_2_0.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.hfad.antiplag_2_0.screens.folders.FolderViewModel
import com.hfad.common_components.dialog.DialogFolder
import com.hfad.common_components.dialog.DialogNotes
import com.hfad.common_components.navigation.LocalNavigator
import com.hfad.common_components.navigation.Routes
import com.hfad.theme.Black
import com.hfad.theme.LiteBlue
import com.hfad.theme.LitePurple
import com.hfad.theme.PointGray
import com.hfad.theme.R
import com.hfad.theme.White
import com.hfad.theme.gray
import kotlinx.coroutines.launch

@Composable
fun SideBarMenu(
    action: @Composable (DrawerState) -> Unit,
    folderViewModel: FolderViewModel
) {
    val scope = rememberCoroutineScope()
    val interactionsSource = remember { MutableInteractionSource() }
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    val configuration = LocalConfiguration.current
    val width = (configuration.screenWidthDp * 0.8).dp

    val navigator = LocalNavigator.current

    val showFolderDialog = remember { mutableStateOf(false) }
    val showNotesDialog = remember { mutableStateOf(false) }

    val currentRoute = navigator.currentRoute

    val folders = folderViewModel.folder.collectAsState()
    val routes = listOf(
        SideBarModel(
            title = "Главная",
            imageId = R.drawable.back,
        ),
        SideBarModel(
            title = "Главная",
            imageId = R.drawable.home,
            route = Routes.HOMESCREEN
        ),
        SideBarModel(
            title = "Редактировать",
            imageId = R.drawable.edit,
            route = Routes.EDITSCREEN
        ),
        SideBarModel(
            title = "Настройки",
            imageId = R.drawable.setting,
            route = Routes.SETTINGSCREEN
        ),

    )
    if (showFolderDialog.value) {
        DialogFolder(
            onDismiss = {
                showFolderDialog.value = false
                scope.launch {
                    drawerState.close()
                }
            },
            onNameSave = { folderName ->
                folderViewModel.addFolder(folderName)
                showFolderDialog.value = false
            },
            title = "Введите название папки"
        )
    }

    if (showNotesDialog.value){
        DialogNotes (
            onDismiss = {
                showNotesDialog.value = false
                scope.launch {
                    drawerState.close()
                }
            }
        )
    }
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
                    ) {
                        scope.launch {
                            drawerState.close()
                        }
                    },
                contentAlignment = Alignment.CenterStart
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .background(White)
                        .width(width),
                    verticalArrangement = Arrangement.spacedBy(7.dp)
                ) {
                    Spacer(modifier = Modifier.height(25.dp))

                    routes[0].Menu(Black) {
                        scope.launch {
                            drawerState.close()
                        }

                    }


                    HorizontalDivider(
                        thickness = 1.dp,
                        color = gray
                    )

                    for (i in 1 until 4) {
                        routes[i].Menu(
                            isSelect = currentRoute == routes[i].route
                        ) {
                            routes[i].route?.let { navigator.navigate(it) }
                        }
                    }
                    HorizontalDivider(
                        Modifier, 1.dp,
                        gray
                    )
                    Text(
                        text = "Папки",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(start = 12.dp)
                    )
                    SideBarModel(
                        title = "Новая папка",
                        imageId = R.drawable.newfolder,
                        isDialog = true
                    ).Menu() {
                        showFolderDialog.value = true
                    }
                    folders.value.forEach {
                        SideBarModel(
                            title = it,
                            imageId = R.drawable.newfolder,

                        ).Menu() {

                        }
                    }
                }
            }
        }
    ) {
        action(drawerState)
    }
}


@Composable
private fun SideBarModel.Menu(
    tint: Color = LiteBlue,
    isSelect: Boolean = false,
    onNavigate: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(if (isSelect) PointGray else Color.Transparent)
            .clickable { onNavigate() }
    ) {

        Row(
            modifier = Modifier
                .background(if (isSelect) PointGray else Color.Transparent)
                .height(44.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (isSelect) {
                Box(
                    modifier = Modifier
                        .width(4.dp)
                        .fillMaxHeight()
                        .background(LitePurple)
                )
            } else {
                Spacer(modifier = Modifier.width(4.dp))
            }

            Spacer(modifier = Modifier.width(12.dp))

            Icon(
                painterResource(imageId),
                contentDescription = null,
                tint = tint
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium
            )

        }
    }

}