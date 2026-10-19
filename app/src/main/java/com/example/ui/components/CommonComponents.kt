package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Audiotrack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.audio.AudioPlaybackState
import com.example.data.model.Hymn

// TRIUNE Colors - Royal Blue & Gold
private val RoyalBlue = Color(0xFF0A1F8F)
private val Gold = Color(0xFFFFD700)
private val GoldLight = Color(0xFFFFE55C)
private val GoldDark = Color(0xFFC5A000)

@Composable
fun ChurchLogo(
    modifier: Modifier = Modifier,
    size: Int = 80
) {
    Box(
        modifier = modifier
            .size(size.dp)
            .clip(CircleShape)
            .border(
                width = 2.dp,
                brush = Brush.linearGradient(
                    colors = listOf(GoldLight, Gold, GoldDark)
                ),
                shape = CircleShape
            )
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Filled.Audiotrack,
            contentDescription = "Church Logo",
            tint = Gold,
            modifier = Modifier.size((size * 0.6).dp)
        )
    }
}

@Composable
fun HymnCardItem(
    hymn: Hymn,
    playbackState: AudioPlaybackState,
    isFavorite: Boolean,
    onPlayPause: () -> Unit,
    onFavorite: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Filled.Audiotrack, contentDescription = null, tint = RoyalBlue)
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(hymn.title, fontWeight = FontWeight.Bold, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Text(hymn.id.toString(), fontSize = 12.sp, color = Color.Gray)
            }
            IconButton(onClick = onPlayPause) {
                Icon(
                    if (playbackState.isPlaying) Icons.Filled.Pause else Icons.Filled.PlayArrow,
                    contentDescription = null
                )
            }
            IconButton(onClick = onFavorite) {
                Icon(
                    if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = null,
                    tint = if (isFavorite) Color.Red else Color.Gray
                )
            }
        }
    }
}
