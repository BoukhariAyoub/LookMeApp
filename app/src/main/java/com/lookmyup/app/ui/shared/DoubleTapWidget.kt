package com.lookmyup.app.ui.shared

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun DoubleTapLikeableWidget(
    isLiked: Boolean,
    onDoubleTap: () -> Unit,
    widget: @Composable () -> Unit,
) {
    var showHeart by remember { mutableStateOf(false) }

    Column {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(isLiked) {
                    detectTapGestures(
                        onDoubleTap = {
                            onDoubleTap()
                            showHeart = true
                        }
                    )
                },
            contentAlignment = Alignment.Center
        ) {
            widget()
            AnimatedHeart(showHeart) {
                showHeart = false
            }
        }
    }
}

@Composable
fun AnimatedHeart(
    isVisible: Boolean,
    onAnimationEnd: () -> Unit
) {
    if (isVisible) {
        LaunchedEffect(Unit) {
            delay(600)
            onAnimationEnd()
        }
    }

    AnimatedVisibility(
        visible = isVisible,
        enter = scaleIn(initialScale = 0.5f) + fadeIn(),
        exit = fadeOut()
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(Color.Red.copy(alpha = 0.8f), shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = "Liked",
                tint = Color.White,
                modifier = Modifier.scale(1.5f)
            )
        }
    }
}