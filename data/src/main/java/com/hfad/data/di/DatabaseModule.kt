package com.hfad.data.di

import android.content.Context
import androidx.room.Room.databaseBuilder
import com.hfad.data.dao.FileDao
import com.hfad.data.database.Database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): Database {
        return databaseBuilder(
            context = context,
            klass = Database::class.java,
            name = "database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideFileDao(database: Database): FileDao = database.fileDao()
}