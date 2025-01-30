package com.lookmyup.app.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class PostEntity(
    @PrimaryKey val id: String,
    val mediaUrl: String,
    val mediaType: String,   // "photo" or "video"
    val likeCount: Int,
    val isLiked: Boolean,
    val isSaved: Boolean,
    val commentCount: Int
)