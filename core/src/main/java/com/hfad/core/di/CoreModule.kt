package com.hfad.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)//анотация синглтона
object CoreModule {
    @Provides
    @Singleton
    fun provideJson(): Json = Json {//сборка Json
        ignoreUnknownKeys = true //игнорирование неизвестных ключей
        encodeDefaults = true //изменение json
    }

    @Provides
    @Singleton
    fun provideHTTPClient(json: Json): HttpClient {//сборка client
        return HttpClient(CIO) {
            expectSuccess = true
            install(ContentNegotiation) {
                json(json)
            }
        }
    }
}