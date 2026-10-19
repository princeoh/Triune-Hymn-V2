package com.example.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun HymnListScreen(
    hymns: List<Map<String, Any>>,
    isLoading: Boolean,
    error: String?,
    onHymnClick: (Map<String, Any>) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Triune Hymn", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Text("${hymns.size}/600 Hymns • Firebase: triune-hymn--global", style = MaterialTheme.typography.bodySmall)
        Spacer(Modifier.height(12.dp))

        when {
            isLoading -> {
                CircularProgressIndicator()
                Spacer(Modifier.height(8.dp))
                Text("Loading your 600 hymns...")
            }
            error != null -> {
                Text("Error: $error", color = MaterialTheme.colorScheme.error)
            }
            else -> {
                LazyColumn {
                    items(hymns) { h ->
                        Card(
                            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                            onClick = { onHymnClick(h) }
                        ) {
                            Column(Modifier.padding(16.dp)) {
                                Text("${h["id"]}. ${h["title"] ?: "Hymn"}", fontWeight = FontWeight.Medium)
                                Text("${h["author"] ?: ""}", style = MaterialTheme.typography.bodySmall)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HymnDetailScreen(
    hymn: Map<String, Any>,
    onBack: () -> Unit
) {
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("${hymn["id"]}. ${hymn["title"]}", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(12.dp))
        Text("Author: ${hymn["author"] ?: ".."} | Category: ${hymn["category"] ?: ""}")
        Spacer(Modifier.height(16.dp))
        Divider()
        Spacer(Modifier.height(16.dp))
        Text("${hymn["verse_1"] ?: ""}\n\nChorus: ${hymn["chorus"] ?: ""}\n\n${hymn["verse_2"] ?: ""}")
        Spacer(Modifier.height(24.dp))
        Button(onClick = onBack, modifier = Modifier.fillMaxWidth()) { Text("Back to List") }
    }
}
