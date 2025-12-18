package com.hfad.data.di

import com.hfad.data.repositoryImpl.TranscriptionRepositoryImpl
import com.hfad.domain.repository.TranscriptionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//сборка репозитория
@Module
@InstallIn(SingletonComponent::class)
abstract class TranscriptionRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindTranscriptionRepository(
        impl: TranscriptionRepositoryImpl
    ): TranscriptionRepository
}