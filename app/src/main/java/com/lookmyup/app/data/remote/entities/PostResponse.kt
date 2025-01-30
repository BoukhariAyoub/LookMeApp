package com.lookmyup.app.data.remote.entities

data class PostResponse(
    val id: String,
    val mediaUrl: String,
    val mediaType: String,
    val likeCount: Int,
    val isLiked: Boolean,
    val isSaved: Boolean,
    val commentCount: Int,
    val items: List<FashionItemResponse>,
)