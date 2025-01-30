package com.lookmyup.app.domain.usecase

import com.lookmyup.app.domain.entities.Post
import com.lookmyup.app.domain.repository.FeedRepository
import kotlinx.coroutines.flow.Flow

class GetFeedUseCase(
    private val repository: FeedRepository
) {
    operator fun invoke(): Flow<List<Post>> {
        return repository.observePostsWithItems()
    }
}