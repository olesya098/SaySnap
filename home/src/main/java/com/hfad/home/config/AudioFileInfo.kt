package com.hfad.home.config

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns

@SuppressLint("Recycle")
fun getFileNameFromUri(context: Context, uri: Uri): String? {
    var fileName: String? = null
    val cursor =
        context.contentResolver.query(uri, arrayOf(OpenableColumns.DISPLAY_NAME), null, null)
    cursor?.use {
        if (it.moveToFirst()) {
            fileName =
                it.getString(it.getColumnIndexOrThrow(OpenableColumns.DISPLAY_NAME))
        }
    }
    return fileName
}