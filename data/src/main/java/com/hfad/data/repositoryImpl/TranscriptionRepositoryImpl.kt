package com.hfad.data.repositoryImpl

import com.hfad.data.data.transcription.TranscriptionService
import com.hfad.domain.model.TranscriptionRequestDTO
import com.hfad.domain.model.UploadRequestDTO
import com.hfad.domain.repository.TranscriptionRepository
import javax.inject.Inject

class TranscriptionRepositoryImpl @Inject constructor(
    private val transcriptionService: TranscriptionService
) : TranscriptionRepository {
    override suspend fun upload(
        token: String,
        filePath: String
    ): UploadRequestDTO {
        return transcriptionService.upload(
            token = token,
            filePath = filePath
        )

    }

    override suspend fun transcription(
        audioUrl: String,
        languageCode: String,
        token: String
    ): TranscriptionRequestDTO {
        return transcriptionService.transcription(
            audioUrl = audioUrl,
            languageCode = languageCode,
            token = token
        )
    }

    override suspend fun getTranscription(
        id: String,
        token: String
    ): TranscriptionRequestDTO {
        return transcriptionService.getTranscription(
            id = id,
            token = token
        )
    }
}