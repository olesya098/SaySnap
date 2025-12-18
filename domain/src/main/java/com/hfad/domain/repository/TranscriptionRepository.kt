package com.hfad.domain.repository

import com.hfad.domain.model.TranscriptionRequestDTO
import com.hfad.domain.model.UploadRequestDTO

interface TranscriptionRepository {
    // связывает app и data

    suspend fun upload(
        token: String,
        filePath: String
    ): UploadRequestDTO

    suspend fun transcription(
        audioUrl: String,
        languageCode: String = "ru",
        token: String,
    ): TranscriptionRequestDTO

    suspend fun getTranscription(
        id: String,
        token: String,
    ): TranscriptionRequestDTO
}