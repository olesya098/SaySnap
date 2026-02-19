package com.hfad.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class GeminiResponseDTO(
    val contents: List<PartsResponseDTO>
)
@Serializable
data class PartsResponseDTO(
   val parts:  List<TextResponseDTO>
)
@Serializable
data class TextResponseDTO(
    val text: String
)