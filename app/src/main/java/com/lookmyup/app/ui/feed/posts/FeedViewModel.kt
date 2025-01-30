package com.lookmyup.app.ui.feed.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lookmyup.app.domain.entities.AppError
import com.lookmyup.app.domain.entities.Post
import com.lookmyup.app.domain.usecase.GetFeedUseCase
import com.lookmyup.app.domain.usecase.RefreshFeedUseCase
import com.lookmyup.app.domain.usecase.ToggleLikeUseCase
import com.lookmyup.app.domain.usecase.ToggleSaveUseCase
import com.lookmyup.app.ui.FeedUiState
import com.lookmyup.app.ui.UiState
import com.lookmyup.app.ui.feed.FeedUiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val getFeedUseCase: GetFeedUseCase,
    private val toggleLikeUseCase: ToggleLikeUseCase,
    private val toggleSaveUseCase: ToggleSaveUseCase,
    private val refreshFeedUseCase: RefreshFeedUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<FeedUiState>(UiState.Loading())
    val uiState: StateFlow<FeedUiState> get() = _uiState

    private val _uiEvents = MutableSharedFlow<FeedUiEvent>()
    val uiEvents = _uiEvents.asSharedFlow()

    init {
        refreshFeed()
        fetchFeed()
    }

    private fun refreshFeed() {
        _uiState.value = UiState.Loading()
        viewModelScope.launch {
            try {
                refreshFeedUseCase()
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Failed to refresh feed: ${e.message}")
            }
        }
    }

    private fun fetchFeed() {
        viewModelScope.launch {
            getFeedUseCase()
                .catch { e ->
                    _uiState.value = UiState.Error(e.message ?: "An unexpected error occurred")
                }
                .collectLatest { posts ->
                    _uiState.value = UiState.Success(posts)
                }
        }
    }

    fun onLikeClicked(post: Post) {
        viewModelScope.launch {
            try {
                toggleLikeUseCase(post)
            } catch (e: AppError) {
                _uiEvents.emit(
                    FeedUiEvent.ShowErrorMessage(
                        e.message ?: "An unexpected error occurred while toggling like"
                    )
                )
            }
        }
    }

    fun onSaveClicked(post: Post) {
        viewModelScope.launch {
            try {
                toggleSaveUseCase(post)
            } catch (e: Exception) {
                _uiEvents.emit(
                    FeedUiEvent.ShowErrorMessage(
                        e.message ?: "An unexpected error occurred while saving post"
                    )
                )
            }
        }
    }

    fun onCommentClicked(post: Post) {
        //TODO : Handle comment click
    }

    fun onShareClicked(post: Post) {
        //TODO : Handle share click
    }

    fun onRemixClicked(post: Post) {
        //TODO : Handle remix click
    }
}