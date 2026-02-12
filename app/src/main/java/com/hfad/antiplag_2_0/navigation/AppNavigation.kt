package com.hfad.antiplag_2_0.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hfad.antiplag_2_0.screens.edit.EditScreen
import com.hfad.antiplag_2_0.screens.edit.EditViewModel
import com.hfad.antiplag_2_0.screens.folders.FolderViewModel
import com.hfad.antiplag_2_0.screens.home.HomeScreen
import com.hfad.antiplag_2_0.screens.home.HomeViewModel
import com.hfad.antiplag_2_0.screens.onboarding.OnboardingScreen
import com.hfad.antiplag_2_0.screens.setting.SettingScreen
import com.hfad.common_components.navigation.LocalNavigator
import com.hfad.common_components.navigation.Navigator
import com.hfad.common_components.navigation.Routes

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val navigator = remember { Navigator() }.apply {
        bind(navController)
    }

    val editViewModel = viewModel<EditViewModel>()
    val homeViewModel = viewModel<HomeViewModel>()
    val folderViewModel = viewModel<FolderViewModel>()

    CompositionLocalProvider(
        LocalNavigator provides navigator
    ) {
        NavHost(
            navController,
            startDestination = Routes.ONBOARDINGSCREEN
        ) {
            composable(route = Routes.HOMESCREEN) {
                HomeScreen(homeViewModel, folderViewModel)
            }
            composable(route = Routes.EDITSCREEN) {
                EditScreen(editViewModel, folderViewModel)
            }
            composable(route = Routes.ONBOARDINGSCREEN) {
                OnboardingScreen()
            }
            composable(route = Routes.SETTINGSCREEN) {
                SettingScreen("account", folderViewModel = folderViewModel) {

                }
            }

        }
    }
}