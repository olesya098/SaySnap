package com.hfad.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class TranscriptionRequestDTO(
    val id: String,
    val status: String,
    val text: String? = null
)