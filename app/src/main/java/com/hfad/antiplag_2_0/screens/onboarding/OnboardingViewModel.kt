package com.hfad.antiplag_2_0.screens.onboarding

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class OnboardingViewModel : ViewModel() {
    private val _currentScreen = MutableStateFlow(0)
    val currentScreen = _currentScreen.asStateFlow()

    private val totalScreen = 4

    fun nextScreen() {
        if (_currentScreen.value < totalScreen - 1) {
            _currentScreen.value += 1
        }
    }

}