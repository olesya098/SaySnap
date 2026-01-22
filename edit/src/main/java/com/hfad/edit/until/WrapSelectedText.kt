package com.hfad.edit.until

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import java.time.format.TextStyle

fun wrapSelectedText (value: TextFieldValue, style: SpanStyle): TextFieldValue {
    val selection = value.selection
    if (selection.collapsed) return value
    val selectedText = value.text.substring(selection.start, selection.end)
    val newText = AnnotatedString.Builder(value.annotatedString).apply {
        addStyle(style, selection.start, selection.end)
    }
    return value.copy(
        annotatedString = newText.toAnnotatedString(),
        selection = selection
    )
}