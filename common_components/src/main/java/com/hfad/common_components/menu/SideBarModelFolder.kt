package com.hfad.common_components.menu

import android.service.autofill.OnClickAction

data class SideBarModelFolder(
    val title: String,
    val imageId: Int,
    val onClick : () -> Unit
)