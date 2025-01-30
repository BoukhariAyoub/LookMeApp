package com.lookmyup.app.ui.feed.posts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
fun FeedListSection(
    uiState: UiState<List<Post>>,
    onLikeClicked: (Post) -> Unit,
    onSaveClicked: (Post) -> Unit,
    onCommentClicked: (Post) -> Unit,
    onShareClicked: (Post) -> Unit,
    onRemixClicked: (Post) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (uiState) {
            is UiState.Loading -> {
                ShimmerLoadingComponent(
                    itemHeight = 200,
                    itemWidth = 1f,
                    itemCount = 3
                )
            }

            is UiState.Success -> {
                FeedListSuccessScreen(
                    posts = uiState.data,
                    onLikeClicked = onLikeClicked,
                    onSaveClicked = onSaveClicked,
                    onCommentClicked = onCommentClicked,
                    onShareClicked = onShareClicked,
                    onRemixClicked = onRemixClicked
                )
            }

            is UiState.Error -> {
                Text(
                    text = uiState.message,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }

}

@Composable
private fun FeedListSuccessScreen(
    posts: List<Post>,
    onLikeClicked: (Post) -> Unit,
    onSaveClicked: (Post) -> Unit,
    onCommentClicked: (Post) -> Unit,
    onShareClicked: (Post) -> Unit,
    onRemixClicked: (Post) -> Unit
) {
    LazyColumn {
        items(posts, key = { it.id }) { post ->
            PostItem(
                post = post,
                onLikeClicked = { onLikeClicked(post) },
                onSaveClicked = { onSaveClicked(post) },
                onCommentClicked = { onCommentClicked(post) },
                onShareClicked = { onShareClicked(post) },
                onRemixClicked = { onRemixClicked(post) }
            )
            Spacer(Modifier.height(24.dp))
        }
    }
}
