package com.hfad.domain.repository

import com.hfad.domain.model.speech.RecognizeResponseDTO

interface SpeechRepository {
    suspend fun sendToGoogleSpeech(audioBase64: String): RecognizeResponseDTO

}