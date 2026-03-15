package com.hfad.antiplag_2_0.screens.auth

import androidx.browser.trusted.Token
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    fun signInGoogle(token: String?) {
        if (token == null) return
        viewModelScope.launch {
            authRepository.sigInGoogle(token)
        }

    }

    fun signOut() {
        authRepository.signOut()
    }
}