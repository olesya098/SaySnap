package com.hfad.data.repositoryImpl

import com.hfad.core.ClientCore
import com.hfad.data.data.geminiservice.GeminiService
import com.hfad.domain.model.GeminiResponseDTO
import com.hfad.domain.model.PartsResponseDTO
import com.hfad.domain.model.TextResponseDTO
import com.hfad.domain.model.TextStructureDTO
import com.hfad.domain.repository.TextStructureRepository
import javax.inject.Inject

class TextStructureRepositoryImpl @Inject constructor(
    private val geminiService: GeminiService,
    private val clientCore: ClientCore
) :
    TextStructureRepository {
    override suspend fun textStructure(text: String): TextStructureDTO {
        val promt = """
Структурируй предоставленный текст: улучши читаемость, раздели на логические абзацы, сохрани исходный смысл и все детали.

Требования к ответу:
- Ответ ТОЛЬКО в формате JSON, без каких-либо пояснений, комментариев или markdown
- Язык ответа: русский
- JSON должен содержать единственный параметр "text" типа String
- Внутри строки используй \n для переносов строк

Пример формата ответа:
{"text": "Структурированный текст здесь..."}

Текст для структурирования:
$text
""".trimIndent()
        val geminiResponse = GeminiResponseDTO(
            contents = listOf(
                PartsResponseDTO(
                    parts = listOf(
                        TextResponseDTO(
                            text = promt
                        )
                    )
                )
            )
        )
        val response = geminiService.generateContent(geminiResponse)
        val jsonResponse = response.candidates.first().content.parts.first().text
        val cleanJson = jsonResponse.removePrefix("```json\n").removeSuffix("```").trim()
        return clientCore.serializer.decodeFromString<TextStructureDTO>(cleanJson)
    }
}