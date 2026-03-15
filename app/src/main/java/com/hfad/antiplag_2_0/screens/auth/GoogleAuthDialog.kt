package com.hfad.antiplag_2_0.screens.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.hfad.antiplag_2_0.R

@Composable
fun GoogleAuthDialog(
    authViewModel: AuthViewModel,
    onDismiss: () -> Unit
) {
    val user = authViewModel.authState.collectAsState()
    val clientId = stringResource(R.string.client_id)

    LaunchedEffect(user.value) {
        if (user.value != null){
            onDismiss()
        }
    }

    Dialog(
        onDismiss,

    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Для проболжения работы войдите в аккаунт"
            )
            Button(
                onClick = {
                    authViewModel.signInGoogle(clientId)
                }
            ) {
                Text(
                    text = "Авторизация Google"
                )
            }
        }
    }

}