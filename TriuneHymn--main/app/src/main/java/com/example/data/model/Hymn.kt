package com.example.data.model

data class Hymn(
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
    val isDownloaded: Boolean = false,
    val localAudioPath: String = "",
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
) {
    val formattedNumber: String
        get() = hymnNumber.toString().padStart(3, '0')
}
