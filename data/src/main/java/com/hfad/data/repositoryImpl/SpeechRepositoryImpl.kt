package com.hfad.data.repositoryImpl

import com.hfad.data.data.speech.SpeechService
import com.hfad.domain.model.speech.RecognizeResponseDTO
import com.hfad.domain.repository.SpeechRepository
import javax.inject.Inject

class SpeechRepositoryImpl @Inject constructor(private val service: SpeechService) : SpeechRepository {
    override suspend fun sendToGoogleSpeech(audioBase64: String): RecognizeResponseDTO {
        return service.sendToGoogleSpeech(audioBase64 = audioBase64)
    }
}