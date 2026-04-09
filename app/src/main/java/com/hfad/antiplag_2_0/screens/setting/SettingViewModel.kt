package com.hfad.antiplag_2_0.screens.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.hfad.antiplag_2_0.network.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SettingViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    private var _user = MutableStateFlow(auth.currentUser)
    var user = _user.asStateFlow()

    private val authStateListener = FirebaseAuth.AuthStateListener {
        _user.value = it.currentUser
    }

    init {
        auth.addAuthStateListener(authStateListener)
    }

    fun signOut() {
        auth.signOut()
    }

    override fun onCleared() {
        super.onCleared()
        auth.removeAuthStateListener(authStateListener)
    }

}