package com.hfad.antiplag_2_0.screens.home

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.hfad.common_components.button.CustomButton
import com.hfad.common_components.menu.SideBarMenu
import com.hfad.common_components.musicFile.MusicFile
import com.hfad.common_components.scaffold.CustomScaffold
import com.hfad.home.components.UploadFile
import com.hfad.home.config.getFileNameFromUri
import com.hfad.theme.LitePurple
import com.hfad.theme.R
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel
) {
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()
    val context = LocalContext.current
    val loadAudio = homeViewModel.loadAudio.collectAsState()

    val audioPickerLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.OpenDocument()) { uri ->
            uri?.let {
                homeViewModel.setAudioUri(it)
            }
        }

    SideBarMenu(
        action = { drawerState ->
            CustomScaffold(
                title = "Home",
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
            ) {
                loadAudio.value?.let {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        MusicFile(
                            text = getFileNameFromUri(
                                context,
                                it
                            ).toString(),
                            onDelete = {
                                homeViewModel.clearAudio()
                            }
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Box(
                            modifier = Modifier.padding(start = 15.dp, end = 15.dp, bottom = 10.dp)
                        ) {
                            CustomButton(
                                text = "Распознать текст",
                                color = LitePurple,
                                onClick = {

                                }
                            )
                        }

                    }

                } ?: run {
                    UploadFile(
                        onClick = {
                            audioPickerLauncher.launch(arrayOf("audio/mpeg"))
                        }
                    )
                }

            }

        }
    )

}