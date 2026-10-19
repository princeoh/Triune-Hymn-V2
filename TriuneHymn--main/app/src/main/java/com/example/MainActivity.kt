package com.example.triunehymn // CHANGE to your package if different

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                TriuneApp()
            }
        }
    }
}

@Composable
fun TriuneApp() {
    var showSplash by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(6000) // 6 seconds
        showSplash = false
    }

    if (showSplash) {
        SplashScreenPure()
    } else {
        HomeScreenPure()
    }
}

@Composable
fun SplashScreenPure() {
    Box(modifier = Modifier.fillMaxSize().background(Color(0xFF0D2B8B)), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("T", color = Color.White, fontSize = 100.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(16.dp))
            Text("TRIUNE HYMN", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 24.sp)
            Text("SING. WORSHIP. GROW.", color = Color(0xFFC9A227), letterSpacing = 4.sp, fontSize = 12.sp)
            Spacer(Modifier.height(40.dp))
            CircularProgressIndicator(color = Color(0xFFC9A227))
        }
    }
}

@Composable
fun HomeScreenPure() {
    Box(modifier = Modifier.fillMaxSize().background(Color(0xFFF8F9FF)), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Welcome to", color = Color.Gray)
            Text("TRIUNE HYMN", color = Color(0xFF0D2B8B), fontSize = 32.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(20.dp))
            Card(colors = CardDefaults.cardColors(Color(0xFFF9F3E3))) {
                Text("Daily Devotional loaded", modifier = Modifier.padding(20.dp))
            }
        }
    }
}
