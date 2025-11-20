package com.hfad.data.data.speech

import com.hfad.core.ClientCore
import com.hfad.domain.model.speech.RecognationAudio
import com.hfad.domain.model.speech.RecognationConfig
import com.hfad.domain.model.speech.RecognizeRequestDTO
import com.hfad.domain.model.speech.RecognizeResponseDTO
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.contentType
import javax.inject.Inject

class SpeechService @Inject constructor(private val core: ClientCore) {
    suspend fun sendToGoogleSpeech(audioBase64: String): RecognizeResponseDTO {
        val request = RecognizeRequestDTO(
            config = RecognationConfig(
                encoding = "LINEAR16",
                sampleRateHertz = 16000,
                languageCode = "ru-RU"
            ),
            audio = RecognationAudio(
                content = audioBase64
            )
        )
        val response = core.request(
            "https://speech.googleapis.com/v1/speech:recognize"
        ) {
            method = HttpMethod.Get
            contentType(ContentType.Application.Json)
            setBody(request)
        }
        return core.serializer.decodeFromString<RecognizeResponseDTO>(response.bodyAsText())

    }
}