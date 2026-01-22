package com.hfad.antiplag_2_0.screens.home

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.util.Log.i
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hfad.antiplag_2_0.model.state.HomeUIState
import com.hfad.domain.model.TranscriptionRequestDTO
import com.hfad.domain.repository.TranscriptionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

const val token = "f8f2bd6943244370b31bb3487782294f"

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val transcriptionRepository: TranscriptionRepository,

) : ViewModel() {


    private val _state = MutableStateFlow(HomeUIState())
    val state = _state.asStateFlow()

    fun setAudioUri(uri: Uri) {
        _state.value = HomeUIState(audioUri = uri)
    }

    fun clearAudio() {
        _state.value = HomeUIState()
    }

    fun transcription(context: Context) {
        val uri = _state.value.audioUri ?: return

        viewModelScope.launch {
            try {
                _state.update {
                    it.copy(isLoading = true)
                }
                val filePath = getFilePath(uri, context)

                val upload = transcriptionRepository.upload(token, filePath)

                val transcript =
                    transcriptionRepository.transcription(upload.uploadUrl, token = token)

                val result =
                    getTranscription(transcript.id, token)

                _state.update {
                    it.copy(
                        isLoading = false,
                        transcriptionText = result.text
                    )
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(isLoading = false, error = e.toString())
                }
            }
        }
    }

    @SuppressLint("Recycle")
    private fun getFilePath(uri: Uri, context: Context): String {
        val input = context.contentResolver.openInputStream(uri)//поток
        val file = File(
            context.cacheDir,
            "audio_${System.currentTimeMillis()}.mp3"
        )//текущий файл кешируем и получаем абсолютный путь
        file.outputStream().use {
            input?.copyTo(it)
        }
        return file.absolutePath
    }

    private suspend fun getTranscription(id: String, token: String): TranscriptionRequestDTO {
        while (true) {
            delay(3000L)
            val response = transcriptionRepository.getTranscription(id, token)
            if (response.status == "completed") return response
            if (response.status == "error") error("Не удалось получить транскрипцию")

        }

    }
}

