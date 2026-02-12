package com.hfad.antiplag_2_0.menu

data class SideBarModel(
    val title: String,
    val imageId: Int,
    val route: String? = null,
    val isDialog: Boolean = false
)