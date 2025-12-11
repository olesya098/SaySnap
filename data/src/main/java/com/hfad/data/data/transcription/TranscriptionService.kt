package com.hfad.data.data.transcription

import com.hfad.core.ClientCore
import com.hfad.domain.model.TranscriptionRequestDTO
import com.hfad.domain.model.UploadRequestDTO
import io.ktor.client.call.body
import io.ktor.client.request.header
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.contentType
import java.io.File
import javax.inject.Inject

class TranscriptionService @Inject constructor(
    private val core: ClientCore
) {

    suspend fun upload(
        token: String,
        filePath: String
    ): UploadRequestDTO {
        val file = File(filePath)
        val response = core.request(
            "https://api.assemblyai.com/v2/upload"
        ) {
            method = HttpMethod.Post
            header(
                HttpHeaders.Authorization,
                token
            )
            contentType(
                ContentType.Application.Json
            )
            setBody(
                file.readBytes()
            )
        }
        return response.body()
    }

    suspend fun transcription(
        audioUrl: String,
        languageCode: String = "ru",
        token: String,
    ): TranscriptionRequestDTO {
        val body = mapOf(
            "audio_url" to audioUrl,
            "language_code" to languageCode
        )
        val response = core.request(
            "https://api.assemblyai.com/v2/transcript"
        ) {
            method = HttpMethod.Post
            header(
                HttpHeaders.Authorization,
                token
            )
            contentType(
                ContentType.Application.Json
            )
            setBody(
                body
            )
        }
        return response.body()
    }

    suspend fun getTranscription(
        id: String,
        token: String,
    ): TranscriptionRequestDTO {
        val response = core.request(
            "https://api.assemblyai.com/v2/transcript/$id"
        ) {
            method = HttpMethod.Get
            header(
                HttpHeaders.Authorization,
                token
            )
        }
        return response.body()
    }

}