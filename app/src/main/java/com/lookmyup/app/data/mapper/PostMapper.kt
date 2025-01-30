package com.lookmyup.app.data.mapper

import com.lookmyup.app.data.local.entities.FashionItemEntity
import com.lookmyup.app.data.local.entities.PostEntity
import com.lookmyup.app.data.local.entities.PostWithItemsEntity
import com.lookmyup.app.data.remote.entities.FashionItemResponse
import com.lookmyup.app.data.remote.entities.PostResponse
import com.lookmyup.app.domain.entities.FashionItem
import com.lookmyup.app.domain.entities.MediaType
import com.lookmyup.app.domain.entities.Post

fun PostResponse.toEntity(): PostEntity = PostEntity(
    id = id,
    mediaUrl = mediaUrl,
    mediaType = mediaType,
    likeCount = likeCount,
    isLiked = isLiked,
    isSaved = isSaved,
    commentCount = commentCount,
)

fun FashionItemResponse.toEntity(postId: String): FashionItemEntity = FashionItemEntity(
    postId = postId,
    name = name,
    imageUrl = imageUrl,
    shoppingUrl = shoppingUrl,
    tag = tag
)

fun PostWithItemsEntity.toDomain(): Post = Post(
    id = post.id,
    mediaUrl = post.mediaUrl,
    mediaType = post.mediaType.toMediaType(),
    likeCount = post.likeCount,
    isLiked = post.isLiked,
    isSaved = post.isSaved,
    commentCount = post.commentCount,
    items = items.map { it.toDomain() }
)

private fun String.toMediaType(): MediaType = when (this) {
    "photo" -> MediaType.PHOTO
    "video" -> MediaType.VIDEO
    else -> throw IllegalArgumentException("Unknown media type: $this")
}

private fun FashionItemEntity.toDomain(): FashionItem = FashionItem(
    name = name,
    imageUrl = imageUrl,
    shoppingUrl = shoppingUrl,
    tag = tag
)


