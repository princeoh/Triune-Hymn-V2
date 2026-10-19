package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Room Entity class defining the 'categories' table schema for offline storage.
 */
@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val description: String = "",
    val iconName: String = "",
    val displayOrder: Int = 0
)
