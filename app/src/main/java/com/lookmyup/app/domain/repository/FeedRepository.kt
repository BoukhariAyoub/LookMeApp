package com.lookmyup.app.domain.repository

import com.lookmyup.app.domain.entities.Post
import kotlinx.coroutines.flow.Flow


interface FeedRepository {
    fun observePostsWithItems(): Flow<List<Post>>
    suspend fun fetchFeed()
    suspend fun toggleLike(post: Post)
    suspend fun toggleSave(post: Post)
}
