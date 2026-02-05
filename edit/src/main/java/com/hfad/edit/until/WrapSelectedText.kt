package com.hfad.edit.until

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.input.TextFieldValue

fun wrapSelectedText (value: TextFieldValue, style: SpanStyle): TextFieldValue {
    val selection = value.selection
    if (selection.collapsed) return value
    val newText = AnnotatedString.Builder(value.annotatedString).apply {
        addStyle(style, selection.start, selection.end)
    }
    return value.copy(
        annotatedString = newText.toAnnotatedString(),
        selection = selection
    )
}