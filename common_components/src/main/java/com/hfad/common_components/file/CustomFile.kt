package com.hfad.common_components.file

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.hfad.common_components.R
import com.hfad.theme.LitePurple
import com.hfad.theme.White

@Composable
fun CustomFile() {
    Row(
        modifier = Modifier
            .background(
                color = LitePurple,
                shape = RoundedCornerShape(17.dp)
            ),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Image(
            painter = painterResource(id = R.drawable.file),
            contentDescription = null,
            modifier = Modifier.size(39.dp)
        )
        Column (
            modifier = Modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){
            Box(
                modifier = Modifier
                    .background(
                        color = White,
                        shape = RoundedCornerShape(13.dp)
                    )
            ){
                Text(
                    text = "Три дня дождя",
                    style = MaterialTheme.typography.bodyMedium,

                )
            }
            Box(
                modifier = Modifier
                    .background(
                        color = White,
                        shape = RoundedCornerShape(13.dp)
                    )
            )

        }


    }
}