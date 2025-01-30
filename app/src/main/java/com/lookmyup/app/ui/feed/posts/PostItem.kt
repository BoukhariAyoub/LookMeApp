package com.lookmyup.app.ui.feed.posts

import FashionItems
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.lookmyup.app.domain.entities.MediaType
import com.lookmyup.app.domain.entities.Post
import com.lookmyup.app.ui.shared.DoubleTapLikeableWidget
import com.lookmyup.app.ui.shared.VideoPlayer

@Composable
fun PostItem(
    post: Post,
    onLikeClicked: () -> Unit,
    onSaveClicked: () -> Unit,
    onCommentClicked: () -> Unit,
    onShareClicked: () -> Unit,
    onRemixClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            MediaItem(
                mediaType = post.mediaType,
                mediaUrl = post.mediaUrl,
                isLiked = post.isLiked,
                onLikeClicked = onLikeClicked
            )

            PostIconsRow(
                post,
                onLikeClicked,
                onSaveClicked,
                onCommentClicked,
                onRemixClicked,
                onShareClicked
            )

            // Shoppable Items in post
            FashionItems(items = post.items)
        }
    }
}

@Composable
private fun MediaItem(
    mediaType: MediaType,
    mediaUrl: String,
    isLiked: Boolean,
    onLikeClicked: () -> Unit
) {
    var liked by remember { mutableStateOf(isLiked) }

    Box(modifier = Modifier.aspectRatio(4 / 5f)) {
        when (mediaType) {
            MediaType.PHOTO -> {
                DoubleTapLikeableWidget(
                    isLiked = isLiked,
                    onDoubleTap = {
                        liked = !liked
                        onLikeClicked()
                    }, {
                        SubcomposeAsyncImage(
                            model = mediaUrl,
                            contentDescription = "Post Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize(),
                            loading = {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator()
                                }
                            },
                            error = {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "Failed to load image",
                                        color = MaterialTheme.colorScheme.error
                                    )
                                }
                            }
                        )
                    }
                )
            }

            MediaType.VIDEO -> {
                VideoPlayer(videoUrl = mediaUrl)
            }
        }
    }
}


@Composable
fun RemixButton(
    icon: ImageVector,
    onClick: () -> Unit
) {
    var isClicked by remember { mutableStateOf(false) }

    val rotation by animateFloatAsState(
        targetValue = if (isClicked) 360f else 0f,
        animationSpec = tween(durationMillis = 600, easing = FastOutSlowInEasing),
        label = "Rotation Animation"
    )

    IconButton(onClick = {
        isClicked = !isClicked
        onClick()
    }) {
        Icon(
            imageVector = icon,
            contentDescription = "Remix",
            modifier = Modifier
                .size(24.dp)
                .rotate(rotation)
        )
    }
}