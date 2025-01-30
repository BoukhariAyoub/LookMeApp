package com.lookmyup.app.ui.feed

sealed class FeedUiEvent {
    data class ShowErrorMessage(val message: String) : FeedUiEvent()
}

sealed class TodayThemeUiEvent {
    data object NavigateToCreatePost : TodayThemeUiEvent()
}