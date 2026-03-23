package com.hfad.antiplag_2_0.network

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.oAuthCredential
import com.hfad.antiplag_2_0.MainActivity
import dagger.hilt.android.internal.Contexts
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class AuthRepository {
    private val auth = FirebaseAuth.getInstance()
    val currentUser: FirebaseUser? = auth.currentUser

    val authState: Flow<FirebaseUser?> = callbackFlow {
        val listener = FirebaseAuth.AuthStateListener {
            trySend(it.currentUser)
        }
        auth.addAuthStateListener(listener)
        awaitClose { auth.removeAuthStateListener(listener) }
    }

    suspend fun sigInGoogle(token: String): Result<FirebaseUser> {
        val credential = GoogleAuthProvider.getCredential(token, null)
        return try {
            val result = auth.signInWithCredential(credential).await()
            Result.success(result.user ?: throw Exception())
        } catch (e: Exception) {
            Log.e("AUTH2", e.toString())
            Result.failure(e)
        }
    }

    suspend fun getTokenId(clientId: String, activity: Activity): String? {
        return try {
            val manager = CredentialManager.create(activity)
            val option = GetSignInWithGoogleOption.Builder(clientId).build()
            val request = GetCredentialRequest.Builder()
                .addCredentialOption(option)
                .build()
            val result = manager.getCredential(activity, request)
            val credential = GoogleIdTokenCredential.createFrom(result.credential.data)
            credential.idToken
        } catch (e: Exception) {
            Log.e("AUTH1", e.toString())
            null
        }
    }

    fun signOut() {
        auth.signOut()
    }
}