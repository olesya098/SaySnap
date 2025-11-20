package com.hfad.domain.model.speech

import kotlinx.serialization.Serializable

@Serializable
data class RecognizeRequestDTO(
    val config: RecognationConfig,
    val audio: RecognationAudio
)
