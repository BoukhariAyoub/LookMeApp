package com.lookmyup.app.domain.entities

data class Post(
    val id: String,
    val mediaUrl: String,
    val mediaType: MediaType,
    val likeCount: Int,
    val isLiked: Boolean,
    val isSaved: Boolean,
    val commentCount: Int,
    val items: List<FashionItem>
)

enum class MediaType {
    PHOTO, VIDEO
}