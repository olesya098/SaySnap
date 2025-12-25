package com.hfad.antiplag_2_0.screens.home

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hfad.domain.repository.TranscriptionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

const val token = "f8f2bd6943244370b31bb3487782294f"

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val transcriptionRepository: TranscriptionRepository
) : ViewModel() {

    private val uploadUrl = MutableStateFlow<String?>(null)

    fun upload(filePath: String) {
        viewModelScope.launch {
            try {
                uploadUrl.value =
                    transcriptionRepository.upload(token = token, filePath = filePath).uploadUrl
            } catch (e: Exception) {
                Log.d("HomeViewModel", "Error fun upload")
            }

        }

    }


}