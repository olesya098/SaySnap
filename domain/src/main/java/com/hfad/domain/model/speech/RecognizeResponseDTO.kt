package com.hfad.domain.model.speech

import kotlinx.serialization.Serializable

@Serializable
data class RecognizeResponseDTO(
    val results: List<SpeechRecognitionResult>,
    val totalBilledTime: String,
    val speechAdaptationInfo: SpeechAdaptationInfo,
    val requestId: String,
    val usingLegacyModels: Boolean
)
