package com.hfad.antiplag_2_0.screens.auth

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.FirebaseUser
import com.hfad.antiplag_2_0.network.AuthRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AuthViewModel(
    private val authRepository: AuthRepository = AuthRepository()
) : ViewModel() {
    val authState: StateFlow<FirebaseUser?> = authRepository.authState
        .stateIn(//преобразовываем холодный поток в горячий
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),//дергает поток каждые 5 секунд
            authRepository.currentUser
        )
    fun signInGoogle(clientId: String?, activity: Activity) {
        Log.d("AUTH", "signInGoogle вызван")
        if (clientId == null) return
        viewModelScope.launch {
            val token = authRepository.getTokenId(clientId, activity) ?: return@launch
            authRepository.sigInGoogle(token)
        }

    }

    fun signOut() {
        authRepository.signOut()
    }
}