package com.hfad.antiplag_2_0.screens.onboarding

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.hfad.common_components.navigation.LocalNavigator
import com.hfad.common_components.navigation.Routes
import com.hfad.common_components.onboarding.SplashComponents
import com.hfad.theme.blueBright
import com.hfad.theme.blueCard
import com.hfad.theme.cyan
import com.hfad.theme.deepBlue
import com.hfad.theme.purple
import com.hfad.theme.purpleBright

@Composable
fun OnboardingScreen(
    viewModel: OnboardingViewModel
) {
    val currentScreen by viewModel.currentScreen.collectAsState()
    val naigator = LocalNavigator.current
    when (currentScreen) {
        0 -> SplashComponents(
            background = deepBlue,
            dividerColor = cyan,
            title = "Автоматическая расшифровка аудио",
            description = "Распознайте текст с помощью передовых нейросетей — быстро, точно, даже в шумной обстановке.",
            image = com.hfad.theme.R.drawable.outline_audio_file_24,
            onNext = { viewModel.nextScreen() },
            currentIndex = currentScreen,
            totalScreens = 4
        )

        1 -> SplashComponents(
            background = blueCard,
            dividerColor = cyan,
            title = "Умное редактирование",
            description = "Нейросеть сама оформит текст: расставит пунктуацию, абзацы и заголовки",
            image = com.hfad.theme.R.drawable.lamp,
            onNext = { viewModel.nextScreen() },
            currentIndex = currentScreen,
            totalScreens = 4
        )

        2 -> SplashComponents(
            background = purple,
            dividerColor = purpleBright,
            title = "Простое редактирование",
            description = "Или отредактируйте вручную — с удобным и интуитивным редактором.",
            image = com.hfad.theme.R.drawable.notes,
            onNext = { viewModel.nextScreen()},
            currentIndex = currentScreen,
            totalScreens = 4
        )

        3 -> SplashComponents(
            background = blueBright,
            dividerColor = cyan,
            title = "Удобное сохранение",
            description = "Приложение даёт возможность сохранить проект как на телефоне, так и в приложении",
            image = com.hfad.theme.R.drawable.save,
            onNext = {
                viewModel.completeOnboarding()
                naigator.navigate(Routes.HOMESCREEN)
                     },
            currentIndex = currentScreen,
            totalScreens = 4
        )
    }
}