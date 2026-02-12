package com.hfad.data.folders

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FolderRepository @Inject constructor(private val dataStore: DataStore<Preferences>) {
    companion object {
        val FOLDER_KEY = stringSetPreferencesKey("folders_key")//ключ для хранения стрингов
    }

    val foldersFlow: Flow<List<String>> =
        dataStore.data.map { preferenses ->//поток преференсов и добавляет данные в поток и обновляет при изменениях
            preferenses[FOLDER_KEY]?.toList() ?: listOf("Общее")//преобразование в лист стрингов
        }

    suspend fun saveFolders(folders: List<String>) {//заходим в хранилище по ключу и сохраняем новый сет данных
        dataStore.edit { preferences ->
            preferences[FOLDER_KEY] = folders.toSet()

        }
    }

    suspend fun saveDefaultFolder() {//для дефолтной папки
        dataStore.edit { preferences ->
            val current = preferences[FOLDER_KEY]
            if (current.isNullOrEmpty()){
                preferences[FOLDER_KEY] = setOf("Общее")
            }

        }
    }
}