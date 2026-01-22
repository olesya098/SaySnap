package com.hfad.home.config

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaMetadataRetriever
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

fun getAudioDuration(context: Context, uri: Uri): Long? {
    val retriever = MediaMetadataRetriever()
    return try {
        retriever.setDataSource(context, uri)
        retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)?.toLong()

    } catch (e: Exception) {
        null
    }
}

fun timeFormat(time: Long): String {
    val totalSecond = time / 1000
    val minutes = totalSecond / 60
    val seconds = totalSecond % 60
    return "%02d:%02d".format(minutes, seconds)
}