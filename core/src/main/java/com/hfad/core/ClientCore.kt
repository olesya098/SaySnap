package com.hfad.core

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.request
import io.ktor.client.statement.HttpResponse
import kotlinx.serialization.json.Json
import javax.inject.Inject

class ClientCore @Inject constructor(
    private val client: HttpClient,
    val serializer: Json
) {
    suspend fun request(
        link: String = "",//ссылка
        block: HttpRequestBuilder.() -> Unit = {}//тело запроса
    ): HttpResponse {
        return client.request(
            link,
            block
        )

    }
}