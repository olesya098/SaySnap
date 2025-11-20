package com.hfad.domain.model.speech

import kotlinx.serialization.Serializable

@Serializable
data class SpeechRecognitionAlternative(
    val transcript: String
)
