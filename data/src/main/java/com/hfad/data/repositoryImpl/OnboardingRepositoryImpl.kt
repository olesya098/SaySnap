package com.hfad.data.repositoryImpl

import android.content.SharedPreferences
import com.hfad.domain.repository.OnboardingRepository
import androidx.core.content.edit

class OnboardingRepositoryImpl(
    private val prefs: SharedPreferences,
) : OnboardingRepository {
    override fun setOnboardingCompleted() {//устанавливается флаг сохранения
        prefs.edit { putBoolean("onboarding_completed", true) }
    }

    override fun isOnboardingCompleted(): Boolean {//проверка флага сохранения
        return prefs.getBoolean("onboarding_completed", false)
    }
}