package com.lookmyup.app.ui.feed.todaytheme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.lookmyup.app.domain.entities.MediaType
import com.lookmyup.app.domain.entities.Post
import com.lookmyup.app.ui.shared.AnimatedText

@Composable
fun TodayThemePosts(
    modifier: Modifier = Modifier,
    themePosts: List<Post>,
    onStoryClicked: (Post) -> Unit,
    onAddPostClicked: () -> Unit
) {
    Box(modifier = modifier.wrapContentSize()) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {

            SectionTitle()
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                item {
                    CreatePostItem(onAddPostClicked)
                }

                items(themePosts, key = { it.id }) { post ->
                    if (post.mediaType == MediaType.VIDEO) {
                        // TODO : Here should probably add thumbnail for video
                        //  but for now, we are skipping it
                        return@items
                    } else {
                        RoundedClickableBox(
                            width = 80.dp,
                            height = 100.dp,
                            onClick = { onStoryClicked(post) },
                            borderColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f)
                        ) {
                            Image(
                                painter = rememberAsyncImagePainter(post.mediaUrl),
                                contentDescription = "Theme Post",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }
            }
        }
    }

}

@Composable
private fun CreatePostItem(onAddPostClicked: () -> Unit) {
    RoundedClickableBox(
        width = 80.dp,
        height = 100.dp,
        onClick = onAddPostClicked,
        backgroundColor = Color.LightGray.copy(alpha = 0.3f),
        borderColor = MaterialTheme.colorScheme.background
    ) {
        Column(
            Modifier.padding(2.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.AddCircle,
                contentDescription = "Add Your Story",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.height(4.dp))

            AnimatedText(
                text = "Want to be featured?",
                textStyle = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(horizontal = 4.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun SectionTitle() {
    Text(
        text = "Today's Theme 🎨",
        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f),
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        maxLines = 1,
        modifier = Modifier.padding(start = 8.dp, bottom = 4.dp)
    )
    Text(
        text = "Winter Wonderland ❄️",
        color = MaterialTheme.colorScheme.primary,
        style = MaterialTheme.typography.displayMedium,
        fontWeight = FontWeight.Bold,
        maxLines = 1,
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 4.dp)
    )
}

@Composable
private fun RoundedClickableBox(
    width: Dp,
    height: Dp,
    onClick: () -> Unit,
    backgroundColor: Color = Color.Transparent,
    borderColor: Color? = null,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = Modifier
            .padding(end = 4.dp)
            .width(width)
            .height(height)
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick() }
            .background(backgroundColor)
            .then(
                if (borderColor != null) Modifier.border(
                    width = 2.dp,
                    color = borderColor,
                    shape = RoundedCornerShape(16.dp)
                ) else Modifier
            ),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}