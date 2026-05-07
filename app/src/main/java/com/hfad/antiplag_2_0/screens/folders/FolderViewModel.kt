package com.hfad.antiplag_2_0.screens.folders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hfad.antiplag_2_0.network.FireStoreRepository
import com.hfad.data.folders.FolderRepository
import com.hfad.domain.model.FileTranscriptionDTO
import com.hfad.domain.model.FolderDTO
import com.hfad.domain.model.TranscriptionRequestDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FolderViewModel @Inject constructor(private val repository: FireStoreRepository) :
    ViewModel() {

    val folders: StateFlow<List<FolderDTO>> = repository.getFolders()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    private val _selectedFolderId = MutableStateFlow<String?>(null)
    val selectedFolderId = _selectedFolderId.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val transcriptions: StateFlow<List<FileTranscriptionDTO>> = _selectedFolderId
        .flatMapLatest {
            repository.getTranscriptionByFolder(it)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun selectFolder(folderId: String?) {
        _selectedFolderId.value = folderId
    }

    fun createFolder(name: String) = viewModelScope.launch {
        repository.createFolder(name)
    }

    fun deleteFolder(folderId: String) = viewModelScope.launch {
        repository.deleteFolder(folderId)
    }

    fun saveFileTranscription(title: String, text: String) = viewModelScope.launch {
        repository.saveFileTranscription(title, text, _selectedFolderId.value)
    }

    fun deleteFileTranscription(fileId: String) = viewModelScope.launch {
        repository.deleteFileTranscription(fileId)
    }

}