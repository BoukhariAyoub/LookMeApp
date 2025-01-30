package com.lookmyup.app.ui.feed.todaytheme

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lookmyup.app.domain.entities.Post
import com.lookmyup.app.ui.UiState
import com.lookmyup.app.ui.shared.ShimmerLoadingComponent


@Composable
fun TodayThemeSection(
    uiState: UiState<List<Post>>,
    onStoryClicked: (Post) -> Unit,
    onAddPostClicked: () -> Unit
) {
    when (uiState) {
        is UiState.Error -> {
            Text(
                text = uiState.message,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.fillMaxSize()
            )
        }

        is UiState.Loading -> {
            Box(Modifier.height(250.dp), contentAlignment = Alignment.Center) {
                ShimmerLoadingComponent(
                    itemHeight = 100,
                    itemWidth = 0.8f,
                    itemCount = 5,
                    isVertical = false
                )
            }
        }

        is UiState.Success -> {
            TodayThemePosts(
                themePosts = uiState.data,
                onStoryClicked = onStoryClicked,
                onAddPostClicked = onAddPostClicked
            )
        }
    }
}