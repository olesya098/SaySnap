package com.hfad.antiplag_2_0.screens.folders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hfad.data.folders.FolderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FolderViewModel @Inject constructor( private val repository: FolderRepository): ViewModel() {
    private val _folder = MutableStateFlow<List<String>>(emptyList())
    val folder = _folder.asStateFlow()

    init {
        viewModelScope.launch {
            repository.saveDefaultFolder()
            repository.foldersFlow.collect {
                _folder.value = it
            }
        }
    }
    fun addFolder(name: String){
        viewModelScope.launch {
            val updated = _folder.value.toMutableList()
            updated.add(name)
            repository.saveFolders(updated)
        }
    }
    fun deleteFolder(name: String){
        viewModelScope.launch {
            val delete = _folder.value.toMutableList()
            delete.remove(name)
            repository.saveFolders(delete)
        }
    }
}