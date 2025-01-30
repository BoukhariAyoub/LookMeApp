package com.lookmyup.app.data.remote.api

import com.lookmyup.app.data.remote.entities.PostResponse

interface FeedApi {
    suspend fun getFeed(): List<PostResponse>
    suspend fun sendLike(postId: String, isLiked: Boolean)
}

