package com.hfad.antiplag_2_0.screens.onboarding

import androidx.lifecycle.ViewModel
import com.hfad.domain.repository.OnboardingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val repository: OnboardingRepository
) : ViewModel() {
    private val _currentScreen = MutableStateFlow(0)
    val currentScreen = _currentScreen.asStateFlow()

    private val totalScreen = 4

    fun nextScreen() {
        if (_currentScreen.value < totalScreen - 1) {
            _currentScreen.value += 1
        }
    }

    fun completeOnboarding() {
        repository.setOnboardingCompleted()
    }

    fun isOnboardingCompleted(): Boolean {
        return repository.isOnboardingCompleted()
    }

}