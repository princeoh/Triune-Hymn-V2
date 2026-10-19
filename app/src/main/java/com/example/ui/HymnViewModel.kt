package com.example.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.entity.HymnEntity
import com.example.data.repository.HymnRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HymnViewModel @Inject constructor(
    private val repository: HymnRepository
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val _isSyncing = MutableStateFlow(false)
    val isSyncing: StateFlow<Boolean> = _isSyncing

    val hymns: StateFlow<List<HymnEntity>> = _searchQuery
        .flatMapLatest { q ->
            if (q.isBlank()) repository.getAllHymns()
            else repository.searchHymns(q)
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }

    fun syncFromFirebase() {
        // Calls your HymnSyncService via Repository
    }
}
