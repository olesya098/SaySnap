package com.hfad.antiplag_2_0.network

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.hfad.domain.model.FileTranscriptionDTO
import com.hfad.domain.model.FolderDTO
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class FireStoreRepository {
    private val db = Firebase.firestore
    private val auth = Firebase.auth
    private val userId = auth.currentUser?.uid ?: error("Пользователь не авторизован")

    private fun folderRef() = db.collection("users").document(userId).collection("folders")

    private fun fileRef() = db.collection("users").document(userId).collection("files")

    suspend fun createFolder(name: String): Result<FolderDTO> = runCatching {
        val folder = FolderDTO(nameFolder = name)
        val ref = folderRef().add(folder).await()
        folder.copy(id = ref.id)
    }

    suspend fun getFolders(): Flow<List<FolderDTO?>> =
        callbackFlow { //получает поток значений фолдеров
            val listener = folderRef()
                .addSnapshotListener { snapshots, exception ->
                    if (exception != null) {
                        close(exception)
                        return@addSnapshotListener
                    }
                    val folders = snapshots?.documents?.map {
                        it.toObject(FolderDTO::class.java)?.copy(id = it.id)
                    } ?: emptyList()
                    trySend(folders)
                }
            awaitClose {
                listener.remove()
            }
        }


    suspend fun deleteFolder(folderId: String): Result<Unit> = runCatching {
        folderRef().document(folderId).delete().await()
        val files = fileRef()
            .whereEqualTo("folderId", folderId).get().await()
        files.documents.forEach {
            it.reference.delete().await()
        }
    }

    suspend fun saveFileTranscription(
        title: String,
        text: String,
        folderId: String?
    ): Result<FileTranscriptionDTO> = runCatching {
        val file = FileTranscriptionDTO(
            title = title,
            text = text,
            folderId = folderId
        )
        val ref = fileRef().add(file).await()
        file.copy(id = ref.id)
    }

    suspend fun deleteFileTranscription(fileId: String): Result<Unit> = runCatching {
        fileRef().document(fileId).delete().await()
    }
}