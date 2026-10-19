package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Room Entity class defining the 'favorites' table schema for offline storage.
 */
@Entity(tableName = "favorites")
data class FavoriteEntity(
    @PrimaryKey
    val hymnId: String,
    val savedAt: Long = System.currentTimeMillis()
)
