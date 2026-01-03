package com.hfad.common_components.menu

data class SideBarModel(
    val title: String,
    val imageId: Int,
    val route: String? = null,
    val isDialog: Boolean = false
)