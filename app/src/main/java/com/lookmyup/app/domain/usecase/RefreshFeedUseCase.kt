package com.lookmyup.app.domain.usecase

import com.lookmyup.app.domain.repository.FeedRepository

class RefreshFeedUseCase(
    private val repository: FeedRepository
) {
    suspend operator fun invoke() {
        return repository.fetchFeed()
    }
}