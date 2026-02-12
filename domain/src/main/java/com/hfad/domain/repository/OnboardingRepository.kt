package com.hfad.domain.repository

interface OnboardingRepository {
    fun setOnboardingCompleted()
    fun isOnboardingCompleted(): Boolean
}