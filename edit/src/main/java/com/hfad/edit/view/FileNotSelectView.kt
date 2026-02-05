package com.hfad.edit.view

import android.service.credentials.Action
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.hfad.common_components.filePicker.FilePicker
import com.hfad.theme.R

@Composable
fun FileNotSelectView(
    action: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Выберите текст для обработки",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(
            modifier = Modifier.height(15.dp)
        )
        Box(
            modifier = Modifier
                .size(120.dp)
                .clickable {

                },
            contentAlignment = Alignment.Center
        ) {
            FilePicker(
                onTextReceived = { text ->
                    action(text)
                },
                modifier = Modifier
                    .size(120.dp)
                    .background(Color.Transparent)
            )

            Image(
                painter = painterResource(id = R.drawable.donloadfile),
                contentDescription = "Выбрать файл",
                modifier = Modifier.size(80.dp)
            )
        }

    }
}