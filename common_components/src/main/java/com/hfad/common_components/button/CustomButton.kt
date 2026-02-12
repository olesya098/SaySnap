package com.hfad.common_components.button

import android.R.attr.text
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hfad.theme.LitePurple
import com.hfad.theme.White
import kotlinx.coroutines.FlowPreview

@Composable
fun CustomButton(
    text: String,
    color: Color,
    modifier: Modifier? = null,
    padding: Dp? = null,
    onClick: () -> Unit
) {
    Button(
        onClick = {
            onClick()

        },
        modifier = Modifier.fillMaxWidth().height(40.dp),
        colors = ButtonDefaults.buttonColors(
            color
        ),
        shape = RoundedCornerShape(13.dp),
    ) {
        Text(
            text = text,
            color = White,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center

        )

    }

}

@Preview
@Composable
fun PreviewCustomButton() {
    CustomButton(
        "Сохранить",
        LitePurple,
        modifier = Modifier,
        onClick = {}

    )
}