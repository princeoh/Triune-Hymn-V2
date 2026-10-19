package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hymns")
data class HymnEntity(
    @PrimaryKey
    val id: String,
    val hymnNumber: Int,
    val title: String,
    val firstLine: String,
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
)
