package com.hfad.antiplag_2_0.screens.home

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.hfad.common_components.button.ButtonBorder
import com.hfad.common_components.button.CustomButton
import com.hfad.home.components.HomeCard
import com.hfad.antiplag_2_0.menu.SideBarMenu
import com.hfad.antiplag_2_0.screens.folders.FolderViewModel
import com.hfad.common_components.musicFile.MusicFile
import com.hfad.common_components.navigation.LocalNavigator
import com.hfad.common_components.navigation.Routes
import com.hfad.common_components.scaffold.CustomScaffold
import com.hfad.home.components.HomeDialog
import com.hfad.home.components.UploadFile
import com.hfad.home.config.getAudioDuration
import com.hfad.home.config.getFileNameFromUri
import com.hfad.home.config.timeFormat
import com.hfad.theme.LitePurple
import com.hfad.theme.R
import com.hfad.theme.background
import com.hfad.theme.gray2
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    folderViewModel: FolderViewModel
) {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        visible = true
    }


    val alpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(800)
    )

    val scope = rememberCoroutineScope()
    val navigator = LocalNavigator.current
    val context = LocalContext.current
    val state = homeViewModel.state.collectAsState()

    val showDialog = remember { mutableStateOf(false) }

    val text = homeViewModel.textStructure.collectAsState()

    LaunchedEffect(state.value.transcriptionText) {
        state.value.transcriptionText?.let { text ->
            showDialog.value = true
            homeViewModel.textStructure(text){
                if (it){
                    showDialog.value = false
                }
            }
        }
    }


    val audioPickerLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.OpenDocument()) { uri ->
            uri?.let {
                homeViewModel.setAudioUri(it)
            }
        }

    if (showDialog.value) {
        HomeDialog()
    }

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


                state.value.audioUri?.let {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(background)
                            .verticalScroll(rememberScrollState()),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(5.dp)
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
                                text.value.toString()
                            )
                        }
                        if (state.value.error != null) {
                            Text(
                                text = state.value.error.toString()
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Box(
                            modifier = Modifier.padding(horizontal = 15.dp)
                        ) {
                            if (state.value.transcriptionText != null) {
                                Column(
                                    modifier = Modifier.fillMaxWidth(),
                                ) {
                                    ButtonBorder(
                                        text = "Структурировать текст"
                                    ) {
                                        navigator.navigate(Routes.EDITSCREEN)
                                    }
                                    CustomButton(
                                        text = "Сохранить",
                                        LitePurple
                                    ) {

                                    }

                                }
                            } else {
                                CustomButton(
                                    text = if (state.value.isLoading) "Распознаю текст" else {
                                        "Распознать текст"
                                    },
                                    color = LitePurple,
                                    onClick = {
                                        homeViewModel.transcription(
                                            context,
                                        ) {}
                                    }
                                )
                            }
                        }

                    }

                } ?: run {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 24.dp)
                            .alpha(alpha),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
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

        }
    )

}