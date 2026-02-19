package com.hfad.data.di

import com.hfad.data.repositoryImpl.TextStructureRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class TextStructureRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindTextStructureRepository(
        impl: TextStructureRepositoryImpl
    ): TextStructureRepositoryModule
}