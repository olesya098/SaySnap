package com.hfad.common_components.filePicker

import android.content.Context
import android.net.Uri
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
import java.io.InputStreamReader
import java.nio.charset.Charset

@Composable
fun FilePicker(
    onTextReceived: (text: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri ->
        uri?.let {
            CoroutineScope(Dispatchers.IO).launch {
                val result = readFileAsText(context, it)
                onTextReceived(result)
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .clickable {
                launcher.launch(
                    arrayOf(
                        "text/plain"
                    )
                )
            }
    )
}

private fun readFileAsText(context: Context, uri: Uri): String {
    return try {
        val bytes = context.contentResolver
            .openInputStream(uri)
            ?.use { it.readBytes() }
            ?: return "НЕ УДАЛОСЬ ПРОЧИТАТЬ ФАЙЛ"

        val utf8Text = bytes.toString(Charsets.UTF_8)
        val text =
            if (utf8Text.contains("�") || utf8Text.contains("?")) {
                bytes.toString(Charset.forName("Windows-1251"))
            } else {
                utf8Text
            }
        text
    } catch (e: Exception) {
        "ОШИБКА ЧТЕНИЯ ФАЙЛА $e"
    }
}