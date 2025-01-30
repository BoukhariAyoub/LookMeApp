package com.lookmyup.app.ui.shared

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun AnimatedText(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle = MaterialTheme.typography.bodySmall,
    color: Color = MaterialTheme.colorScheme.primary,
    textAlign: TextAlign = TextAlign.Center,
    delayMillis: Long = 50,
    restartDelayMillis: Long = 3000
) {
    var displayedText by remember { mutableStateOf("") }

    LaunchedEffect(text) {
        while (true) {
            displayedText = ""
            for (i in text.indices) {
                displayedText = text.substring(0, i + 1)
                delay(delayMillis)
            }
            delay(restartDelayMillis)
        }
    }

    AnimatedVisibility(
        visible = displayedText.isNotEmpty(),
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Text(
            text = displayedText,
            style = textStyle,
            color = color,
            modifier = modifier.padding(horizontal = 4.dp),
            textAlign = textAlign
        )
    }
}