package com.hfad.antiplag_2_0.screens.onboarding

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hfad.antiplag_2_0.R
import com.hfad.common_components.navigation.Routes
import com.hfad.common_components.onboarding.SplashComponents
import com.hfad.theme.EditScreen
import com.hfad.theme.SaveDivider
import com.hfad.theme.SaveScreen
import com.hfad.theme.SimpleEditDivider
import com.hfad.theme.SimpleEditScreen
import com.hfad.theme.TranscriptionDivider
import com.hfad.theme.TranscriptionScreen

@Composable
fun OnboardingScreen() {

    val viewModel: OnboardingViewModel = viewModel()
    val currentScreen by viewModel.currentScreen.collectAsState()
    when (currentScreen) {
        0 -> SplashComponents(
            background = TranscriptionScreen,
            dividerColor = TranscriptionDivider,
            title = "Автоматическая расшифровка аудио",
            description = "Распознайте текст с помощью передовых нейросетей — быстро, точно, даже в шумной обстановке.",
            image = com.hfad.theme.R.drawable.video_and_audio,
            onNext = { viewModel.nextScreen() },
            currentIndex = currentScreen,
            totalScreens = 4
        )

        1 -> SplashComponents(
            background = EditScreen,
            dividerColor = TranscriptionDivider,
            title = "Умное редактирование",
            description = "Нейросеть сама оформит текст: расставит пунктуацию, абзацы и заголовки",
            image = R.drawable.edit,
            onNext = { viewModel.nextScreen() },
            currentIndex = currentScreen,
            totalScreens = 4
        )

        2 -> SplashComponents(
            background = SimpleEditScreen,
            dividerColor = SimpleEditDivider,
            title = "Простое редактирование",
            description = "Или отредактируйте вручную — с удобным и интуитивным редактором.",
            image = com.hfad.theme.R.drawable.edit_document_file_icon,
            onNext = { viewModel.nextScreen() },
            currentIndex = currentScreen,
            totalScreens = 4
        )

        3 -> SplashComponents(
            background = SaveScreen,
            dividerColor = SaveDivider,
            title = "Удобное сохранение",
            description = "Приложение даёт возможность сохранить проект как на телефоне, так и в приложении",
            image = com.hfad.theme.R.drawable.save,
            onNext = { Routes.HOMESCREEN },
            currentIndex = currentScreen,
            totalScreens = 4
        )
    }
}