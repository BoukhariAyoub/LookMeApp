package com.lookmyup.app.ui.feed.posts

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lookmyup.app.domain.entities.Post
import com.lookmyup.app.ui.shared.IconButtonWithBadge
import com.lookmyup.app.ui.theme.AppIcons

@Composable
fun PostIconsRow(
    post: Post,
    onLikeClicked: () -> Unit,
    onSaveClicked: () -> Unit,
    onCommentClicked: () -> Unit,
    onRemixClicked: () -> Unit,
    onShareClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {

        IconButtonWithBadge(
            icon = Icons.Outlined.FavoriteBorder,
            iconSelected = Icons.Filled.Favorite,
            isSelected = post.isLiked,
            count = post.likeCount,
            onClick = onLikeClicked
        )

        IconButton(onClick = onSaveClicked) {
            AnimatedContent(targetState = post.isSaved) { isSaved ->
                Icon(
                    tint = if (isSaved) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary,
                    imageVector = if (isSaved) AppIcons.Bookmark_check else AppIcons.Bookmark,
                    contentDescription = "Save"
                )
            }
        }


        IconButtonWithBadge(
            icon = AppIcons.Comment,
            iconSelected = AppIcons.Comment,
            isSelected = false,
            count = post.commentCount,
            onClick = onCommentClicked
        )

        IconButton(onClick = onRemixClicked) {
            RemixButton(
                icon = AppIcons.Autoplay,
                onClick = onRemixClicked
            )
        }

        IconButton(onClick = onShareClicked) {
            Icon(imageVector = Icons.Outlined.Share, contentDescription = "Remix")
        }
    }
}