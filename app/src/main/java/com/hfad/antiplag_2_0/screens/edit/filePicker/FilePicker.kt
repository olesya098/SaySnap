package com.hfad.antiplag_2_0.screens.edit.filePicker

import android.content.Context
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun FilePicker(
    onTextReceived: (text: String, fileName: String?) -> Unit,
    modifier: Modifier = Modifier,
    fileTypes: Array<String> = arrayOf("*/*")
) {
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let {
            CoroutineScope(Dispatchers.IO).launch {
                val result = readFileAsText(context, it)
                onTextReceived(result.first, result.second)
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .clickable {
                launcher.launch(fileTypes.joinToString(","))
            }
    )
}

private fun readFileAsText(context: Context, uri: android.net.Uri): Pair<String, String?> {
    return try {
        val fileName = uri.lastPathSegment ?: "Файл"
        val text = context.contentResolver.openInputStream(uri)?.use { inputStream ->
            inputStream.bufferedReader().use { reader ->
                reader.readText()
            }
        } ?: "Не удалось прочитать файл"

        Pair(text, fileName)
    } catch (e: Exception) {
        Pair("Ошибка чтения файла: ${e.message}", null)
    }
}