package com.example.data.local.dao

import androidx.room.*
import com.example.data.local.entity.HymnEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HymnDao {
    @Query("SELECT * FROM hymns ORDER BY hymnNumber ASC")
    fun getAllHymns(): Flow<List<HymnEntity>>

    @Query("SELECT * FROM hymns WHERE id = :id")
    suspend fun getHymnById(id: String): HymnEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(hymns: List<HymnEntity>)

    @Query("SELECT * FROM hymns WHERE title LIKE '%' || :query || '%' OR firstLine LIKE '%' || :query || '%'")
    fun searchHymns(query: String): Flow<List<HymnEntity>>
}
