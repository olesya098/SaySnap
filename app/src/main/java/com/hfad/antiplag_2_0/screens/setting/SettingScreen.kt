package com.hfad.antiplag_2_0.screens.setting

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.hfad.antiplag_2_0.menu.SideBarMenu
import com.hfad.antiplag_2_0.screens.folders.FolderViewModel
import com.hfad.common_components.scaffold.CustomScaffold
import com.hfad.theme.LitePurple
import com.hfad.theme.R
import com.hfad.theme.gray
import com.hfad.theme.gray2
import kotlinx.coroutines.launch

@Composable
fun SettingScreen(
    account: String,
    folderViewModel: FolderViewModel,
    onExit: () -> Unit,

) {
    val scope = rememberCoroutineScope()
    SideBarMenu(
        folderViewModel = folderViewModel,
        action = { drawerState ->
            CustomScaffold(
                title = "Setting",
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
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(13.dp),
                    verticalArrangement = Arrangement.spacedBy(18.dp)
                ) {
                    Text(
                        text = "Аккаунт",
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.google),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            text = account,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 10.dp),
                        )
                        Text(
                            text = "Выйти",
                            style = MaterialTheme.typography.labelSmall,
                            modifier = Modifier
                                .clickable {
                                    onExit()
                                }
                        )

                    }
                    HorizontalDivider(
                        Modifier, 1.dp,
                        gray
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.about),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            text = "О программе",
                            modifier = Modifier.padding(horizontal = 10.dp),
                            style = MaterialTheme.typography.bodyLarge
                        )

                    }
                    Text(
                        text = "Версия приложения   0.0.1",
                        style = MaterialTheme.typography.bodyLarge
                    )

                }
            }
        }
    )

}