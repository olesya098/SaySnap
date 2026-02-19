package com.hfad.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class GeminiRequestDTO(
    val candidates: List<BodyRequestDTO>
)

@Serializable
data class BodyRequestDTO(
    val content: PartsRequestDTO
)

@Serializable
data class PartsRequestDTO(
    val parts: List<TextResponseDTO>
)