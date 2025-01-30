package com.lookmyup.app.domain.usecase

import android.accounts.NetworkErrorException
import com.lookmyup.app.domain.entities.AppError
import com.lookmyup.app.domain.entities.Post
import com.lookmyup.app.domain.repository.FeedRepository

class ToggleSaveUseCase(private val repository: FeedRepository) {
    suspend operator fun invoke(post: Post) {
        runCatching {
            repository.toggleSave(post)
        }.onFailure { error ->
            when (error) {
                is NetworkErrorException -> {
                    throw AppError.NetworkError("Network error occurred while saving post")
                }

                else -> {
                    throw AppError.UnexpectedError(error)
                }
            }
        }
    }
}