package com.hfad.antiplag_2_0.screens.edit

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hfad.antiplag_2_0.model.state.EditUIState
import com.hfad.data.dao.FileDao
import com.hfad.data.entity.FileEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class EditViewModel @Inject constructor(
    private val fileDao: FileDao
) : ViewModel() {
    private val _editState = MutableStateFlow<EditUIState>(EditUIState.IsFileNotSelected)
    val editState = _editState.asStateFlow()

    private val _fileText = MutableStateFlow(TextFieldValue(""))
    val fileText = _fileText.asStateFlow()
    fun updateEditState(editUIState: EditUIState) {
        viewModelScope.launch {
            _editState.value = editUIState
        }
    }
    fun editFile(fileEntity: FileEntity) {
        viewModelScope.launch {
            fileDao.updateFile(fileEntity)
        }
    }

    fun updateFileText(fileText: TextFieldValue){
        _fileText.value = fileText
    }
}