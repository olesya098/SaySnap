package com.hfad.domain.model.speech

import kotlinx.serialization.Serializable

@Serializable
data class RecognationAudio(
    val content: String,
    val uri: String? = null
)
