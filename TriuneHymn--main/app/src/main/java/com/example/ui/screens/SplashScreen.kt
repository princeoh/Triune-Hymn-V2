package com.example.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onEnterHymnal: () -> Unit
) {
    // 6s timer logic
    var progress by remember { mutableFloatStateOf(0f) }
    LaunchedEffect(Unit) {
        // Animate progress for 6s
        for (i in 1..60) {
            progress = i / 60f
            delay(100) // 60 * 100ms = 6000ms = 6s
        }
        onEnterHymnal() // Auto enter after 6s
    }

    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val scale by infiniteTransition.animateFloat(
        initialValue = 0.95f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        ), label = "scale"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0D47A1)), // Deep Triune Blue
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.weight(0.5f))

            // LOGO - Replace with your actual logo drawable later
            // For now, gold ring placeholder matching your image
            Box(
                modifier = Modifier
                    .size(180.dp)
                    .clip(RoundedCornerShape(90.dp))
                    .background(Color(0xFFC9A227)), // Gold
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(165.dp)
                        .clip(RoundedCornerShape(90.dp))
                        .background(Color(0xFF0D47A1)),
                    contentAlignment = Alignment.Center
                ) {
                    // TODO: Put your logo drawable here: Image(painterResource(R.drawable.triune_logo))
                    Text(
                        "✝️\nTRIUNE\nEMBASSY",
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                "TRIUNE HYMN",
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Light,
                letterSpacing = 4.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "TRIUNE EMBASSY GLOBAL CHURCH",
                color = Color(0xFF90CAF9),
                fontSize = 12.sp,
                letterSpacing = 2.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Pill
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(Color.Transparent)
                    .padding(horizontal = 1.dp)
            ) {
                Surface(
                    shape = RoundedCornerShape(50),
                    color = Color(0xFF0A2F6B),
                    tonalElevation = 0.dp,
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                ) {
                    Text(
                        "SING. WORSHIP. GROW.",
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp),
                        color = Color(0xFFE3F2FD),
                        fontSize = 12.sp,
                        fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                        letterSpacing = 1.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Psalm Card - matches image
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF1E5AB5).copy(alpha = 0.6f))
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "“O sing unto the Lord a new song: sing unto the Lord, all the earth.”",
                        color = Color.White,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        lineHeight = 22.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        "— PSALM 96:1",
                        color = Color(0xFFFFE082),
                        fontSize = 12.sp,
                        letterSpacing = 1.sp
                    )
                }
            }

            Spacer(modifier = Modifier.weight(0.5f))

            // Gold Progress bar - 6s
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(4.dp)
                    .clip(RoundedCornerShape(50))
                    .background(Color.White.copy(alpha = 0.3f))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(progress)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(50))
                        .background(
                            Brush.horizontalGradient(
                                listOf(Color(0xFFFFD54F), Color(0xFFFFA000))
                            )
                        )
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Enter Button
            Button(
                onClick = onEnterHymnal,
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(48.dp),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFC9A227),
                    contentColor = Color(0xFF0D2B5E)
                )
            ) {
                Text("ENTER HYMNAL", fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
