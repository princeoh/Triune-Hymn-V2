package com.example.data.firestore

import android.content.Context
import android.util.Log
import com.example.data.model.Hymn
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

/**
 * Firestore data model with default values and no-arg constructor
 * for type-safe Firestore serialization.
 */
data class FirestoreHymn(
    val id: String = "",
    val hymnNumber: Int = 0,
    val title: String = "",
    val firstLine: String = "",
    val lyrics: String = "",
    val author: String = "Traditional",
    val composer: String = "Traditional",
    val collection: String = "Triune Embassy Hymnal",
    val categories: List<String> = emptyList(),
    val audioUrl: String = "",
    val audioFileName: String = "",
    val isFeatured: Boolean = false,
    val isPublished: Boolean = true,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
) {
    fun toDomainModel(isDownloaded: Boolean = false, localAudioPath: String = ""): Hymn {
        return Hymn(
            id = id,
            hymnNumber = hymnNumber,
            title = title,
            firstLine = firstLine,
            lyrics = lyrics,
            author = author,
            composer = composer,
            collection = collection,
            categories = categories,
            audioUrl = audioUrl,
            audioFileName = audioFileName,
            isFeatured = isFeatured,
            isPublished = isPublished,
            isDownloaded = isDownloaded,
            localAudioPath = localAudioPath,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }

    companion object {
        fun fromDomainModel(hymn: Hymn): FirestoreHymn {
            return FirestoreHymn(
                id = hymn.id,
                hymnNumber = hymn.hymnNumber,
                title = hymn.title,
                firstLine = hymn.firstLine,
                lyrics = hymn.lyrics,
                author = hymn.author,
                composer = hymn.composer,
                collection = hymn.collection,
                categories = hymn.categories,
                audioUrl = hymn.audioUrl,
                audioFileName = hymn.audioFileName,
                isFeatured = hymn.isFeatured,
                isPublished = hymn.isPublished,
                createdAt = hymn.createdAt,
                updatedAt = hymn.updatedAt
            )
        }
    }
}

class FirestoreHymnDataSource(private val context: Context) {

    private val firestore: FirebaseFirestore? by lazy {
        try {
            if (FirebaseApp.getApps(context).isNotEmpty()) {
                FirebaseFirestore.getInstance()
            } else {
                Log.w("FirestoreHymnDataSource", "FirebaseApp not initialized, cloud sync will use local cache.")
                null
            }
        } catch (e: Exception) {
            Log.w("FirestoreHymnDataSource", "Could not initialize Firestore instance: ${e.message}")
            null
        }
    }

    private val hymnsCollection = "hymns"

    /**
     * Fetches all published hymns from the remote Firestore database.
     */
    suspend fun fetchRemoteHymns(): Result<List<FirestoreHymn>> = withContext(Dispatchers.IO) {
        val db = firestore ?: return@withContext Result.failure(
            IllegalStateException("Firestore is not configured or offline")
        )
        try {
            val snapshot = db.collection(hymnsCollection).get().await()

            val hymns = snapshot.documents.mapNotNull { doc ->
                doc.toObject(FirestoreHymn::class.java)?.copy(id = doc.id)
            }
            Result.success(hymns)
        } catch (e: Exception) {
            Log.e("FirestoreHymnDataSource", "Error fetching hymns from Firestore", e)
            Result.failure(e)
        }
    }

    /**
     * Real-time stream observation of hymns collection from Firestore.
     */
    fun observeRemoteHymns(): Flow<List<FirestoreHymn>> = callbackFlow {
        val db = firestore
        if (db == null) {
            trySend(emptyList())
            close()
            return@callbackFlow
        }

        val listener = db.collection(hymnsCollection)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    Log.w("FirestoreHymnDataSource", "Listen failed: ${error.message}")
                    return@addSnapshotListener
                }
                if (snapshot != null) {
                    val list = snapshot.documents.mapNotNull { doc ->
                        doc.toObject(FirestoreHymn::class.java)?.copy(id = doc.id)
                    }
                    trySend(list)
                }
            }

        awaitClose {
            listener.remove()
        }
    }

    /**
     * Saves or updates a hymn in Cloud Firestore.
     */
    suspend fun saveHymn(hymn: Hymn): Result<Unit> = withContext(Dispatchers.IO) {
        val db = firestore ?: return@withContext Result.failure(
            IllegalStateException("Firestore is not configured or offline")
        )
        try {
            val firestoreHymn = FirestoreHymn.fromDomainModel(hymn)
            db.collection(hymnsCollection)
                .document(hymn.id)
                .set(firestoreHymn, SetOptions.merge())
                .await()
            Result.success(Unit)
        } catch (e: Exception) {
            Log.e("FirestoreHymnDataSource", "Error saving hymn to Firestore", e)
            Result.failure(e)
        }
    }

    /**
     * Deletes or unpublishes a hymn in Cloud Firestore.
     */
    suspend fun deleteHymn(hymnId: String): Result<Unit> = withContext(Dispatchers.IO) {
        val db = firestore ?: return@withContext Result.failure(
            IllegalStateException("Firestore is not configured or offline")
        )
        try {
            db.collection(hymnsCollection)
                .document(hymnId)
                .delete()
                .await()
            Result.success(Unit)
        } catch (e: Exception) {
            Log.e("FirestoreHymnDataSource", "Error deleting hymn from Firestore", e)
            Result.failure(e)
        }
    }
}
