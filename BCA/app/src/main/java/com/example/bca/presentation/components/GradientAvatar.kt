package com.example.bca.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight

@Composable
fun GradientAvatar(name: String, modifier: Modifier = Modifier) {
    val initials = name.firstOrNull()?.uppercase() ?: "?"

    val gradientColors = listOf(
        Color(0xFF6366F1),
        Color(0xFFEC4899)
    )

    Box(
        modifier = modifier
            .size(120.dp)
            .shadow(8.dp, CircleShape)
            .background(brush = Brush.linearGradient(gradientColors), shape = CircleShape)
            .clip(CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = initials,
            style = MaterialTheme.typography.displaySmall.copy(color = Color.White),
            fontWeight = FontWeight.Bold
        )
    }
}
