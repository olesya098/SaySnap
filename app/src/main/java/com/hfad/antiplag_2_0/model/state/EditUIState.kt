package com.hfad.antiplag_2_0.model.state

sealed class EditUIState {
    data object IsFileNotSelected : EditUIState()
    data object IsFileSelected : EditUIState()
}