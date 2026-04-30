package com.hfad.antiplag_2_0.screens.folders

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.hfad.antiplag_2_0.menu.SideBarMenu
import com.hfad.common_components.scaffold.CustomScaffold
import com.hfad.theme.R
import com.hfad.theme.gray2
import kotlinx.coroutines.launch

@Composable
fun FolderScreen(
    folderViewModel: FolderViewModel,
) {
    val scope = rememberCoroutineScope()
    SideBarMenu(
        folderViewModel = folderViewModel,
        action = { drawerState ->
            CustomScaffold(
                title = "Home",
                navigationIcon = {
                    IconButton(
                        onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        },
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .size(30.dp)
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(15.dp)
                            )
                            .border(
                                width = 1.5.dp,
                                color = gray2.copy(alpha = 0.3f),
                                shape = RoundedCornerShape(15.dp)
                            )
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.menu),
                            contentDescription = "Назад",
                            tint = Color(0xFF1A1A1A),
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            ) {

            }
        }
    )
}