package com.example.data.repository
import com.example.data.local.dao.HymnDao
import com.example.data.local.entity.HymnEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton
@Singleton
class HymnRepository @Inject constructor(private val hymnDao: HymnDao) {
    fun getAllHymns(): Flow<List<HymnEntity>> = hymnDao.getAllHymns()
    fun searchHymns(query: String): Flow<List<HymnEntity>> = hymnDao.searchHymns(query)
    suspend fun getHymnById(id: String): HymnEntity? = hymnDao.getHymnById(id)
}
