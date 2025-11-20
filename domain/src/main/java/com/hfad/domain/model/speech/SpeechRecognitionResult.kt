package com.hfad.domain.model.speech

import kotlinx.serialization.Serializable

@Serializable
data class SpeechRecognitionResult(
    val alternatives: List<SpeechRecognitionAlternative>,
    val channelTag: Int,
    val resultEndTime: String,
    val languageCode: String
)
