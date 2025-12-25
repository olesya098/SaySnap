package com.hfad.antiplag_2_0.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hfad.antiplag_2_0.screens.edit.EditScreen
import com.hfad.antiplag_2_0.screens.home.HomeScreen
import com.hfad.antiplag_2_0.screens.onboarding.OnboardingScreen
import com.hfad.common_components.navigation.LocalNavigator
import com.hfad.common_components.navigation.Navigator
import com.hfad.common_components.navigation.Routes

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val navigator = remember { Navigator() }.apply {
        bind(navController)
    }
    CompositionLocalProvider(
        LocalNavigator provides navigator
    ) {
        NavHost(
            navController,
            startDestination = Routes.ONBOARDINGSCREEN
        ){
            composable (route = Routes.HOMESCREEN){
                HomeScreen()
            }
            composable (route = Routes.EDITSCREEN) {
                EditScreen()
            }
            composable(route = Routes.ONBOARDINGSCREEN) {
                OnboardingScreen()
            }

        }
    }
}