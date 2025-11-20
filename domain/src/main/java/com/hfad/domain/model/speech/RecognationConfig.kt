package com.hfad.domain.model.speech

import kotlinx.serialization.Serializable

@Serializable
data class RecognationConfig(
    val encoding: String,
    val sampleRateHertz: Int,
    val audioChannelCount: Int? = null,
    val enableSeparateRecognitionPerChannel: Boolean? = null,
    val languageCode: String,
    val alternativeLanguageCodes: List<String>? = null,
    val maxAlternatives: Int? = null,
    val profanityFilter: Boolean? = null,
    val enableWordTimeOffsets: Boolean? = null,
    val enableWordConfidence: Boolean? = null,
    val enableAutomaticPunctuation: Boolean? = null,
    val enableSpokenPunctuation: Boolean? = null,
    val enableSpokenEmojis: Boolean? = null,
    val model: String? = null,
    val useEnhanced: Boolean = true,
)
