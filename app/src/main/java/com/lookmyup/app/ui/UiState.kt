package com.lookmyup.app.ui

import com.lookmyup.app.domain.entities.Post

sealed class UiState<T> {
    class Loading<T> : UiState<T>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error<T>(val message: String) : UiState<T>()
}

typealias FeedUiState = UiState<List<Post>>
typealias TodayThemeUiState = UiState<List<Post>>
