package com.hfad.domain.model

data class FileTranscriptionDTO(
    val id: String = "",
    val title: String = "",
    val text: String = "",
    val folderId: String? = null
)