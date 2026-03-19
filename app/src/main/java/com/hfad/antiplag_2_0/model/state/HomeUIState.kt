package com.hfad.antiplag_2_0.model.state

import android.net.Uri

data class HomeUIState(
    val audioUri: Uri? = null,
    val isLoading: Boolean = false,
    val transcriptionText: String? = null,
    val error: String? = null,
)