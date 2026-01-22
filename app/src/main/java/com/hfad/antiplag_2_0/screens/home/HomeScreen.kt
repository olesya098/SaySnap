package com.hfad.antiplag_2_0.screens.home

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.hfad.common_components.button.ButtonBorder
import com.hfad.common_components.button.CustomButton
import com.hfad.common_components.dialog.Dialog
import com.hfad.common_components.homeCard.HomeCard
import com.hfad.common_components.menu.SideBarMenu
import com.hfad.common_components.musicFile.MusicFile
import com.hfad.common_components.navigation.Routes
import com.hfad.common_components.scaffold.CustomScaffold
import com.hfad.home.components.UploadFile
import com.hfad.home.config.getAudioDuration
import com.hfad.home.config.getFileNameFromUri
import com.hfad.home.config.timeFormat
import com.hfad.theme.LitePurple
import com.hfad.theme.PointGray
import com.hfad.theme.R
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel
) {
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()
    val context = LocalContext.current
    val state = homeViewModel.state.collectAsState()

    var showDialog = remember { mutableStateOf(false) }
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
                if (showDialog.value){
                    Dialog (
                        onDismiss = {
                            showDialog.value = false
                            scope.launch {
                                drawerState.close()
                            }
                        }
                    )
                }

                state.value.audioUri?.let {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        val duration = getAudioDuration(context, it)
                        MusicFile(
                            text = getFileNameFromUri(
                                context,
                                it
                            ).toString(),
                            time = timeFormat(duration ?: 0),
                            onDelete = {
                                homeViewModel.clearAudio()
                            }
                        )
                        if (state.value.transcriptionText != null) {
                            HomeCard(
                                state.value.transcriptionText.toString()
                            )
                        }
                        if (state.value.error != null) {
                            Text(
                                text = state.value.error.toString()
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Box(
                            modifier = Modifier.padding(start = 15.dp, end = 15.dp, bottom = 10.dp)
                        ) {
                            if (state.value.transcriptionText != null) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    ButtonBorder(
                                        text = "Структурировать текст"
                                    ) {
                                        navController.navigate(Routes.EDITSCREEN)

                                    }
                                    CustomButton(
                                        text = "Сохранить",
                                        LitePurple
                                    ) {
                                        showDialog.value = true
                                    }

                                }
                            } else {
                                CustomButton(
                                    text = if (state.value.isLoading) "Распознаю текст" else {
                                        "Распознать текст"
                                    },
                                    color = LitePurple,
                                    onClick = {
                                        homeViewModel.transcription(context)
                                    }
                                )
                            }
                        }

                    }

                } ?: run {
                    UploadFile(
                        onClick = {
                            audioPickerLauncher.launch(
                                arrayOf(
                                    "audio/mpeg",
                                    "audio/mp4",
                                    "audio/mp4a-latm"
                                )
                            )
                        }
                    )
                }

            }

        }
    )

}