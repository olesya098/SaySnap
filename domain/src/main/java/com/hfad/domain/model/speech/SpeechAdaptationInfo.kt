package com.hfad.domain.model.speech

import kotlinx.serialization.Serializable

@Serializable
data class SpeechAdaptationInfo(
    val adaptationTimeout: Boolean,
    val timeoutMessage: String
)
