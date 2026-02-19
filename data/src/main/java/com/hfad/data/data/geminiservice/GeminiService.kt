package com.hfad.data.data.geminiservice

import com.hfad.core.ClientCore
import com.hfad.domain.model.GeminiRequestDTO
import com.hfad.domain.model.GeminiResponseDTO
import io.ktor.client.call.body
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.contentType
import io.ktor.http.headers
import javax.inject.Inject

class GeminiService @Inject constructor(
    private val core: ClientCore
) {
    suspend fun generateContent(
        geminiResponseDTO: GeminiResponseDTO
    ): GeminiRequestDTO {
        return core.request(
            "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash-lite:generateContent"
        ) {
            method = HttpMethod.Post
            headers{
                append("x-goog-api-key","AIzaSyBRZCO55eA63agLv39SM3Le3meUa49rGmU")
            }
            contentType(
                ContentType.Application.Json
            )
            setBody(geminiResponseDTO)
        }.body()
    }
}