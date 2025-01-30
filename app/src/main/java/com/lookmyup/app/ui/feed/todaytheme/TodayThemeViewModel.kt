package com.lookmyup.app.ui.feed.todaytheme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lookmyup.app.domain.entities.Post
import com.lookmyup.app.domain.usecase.GetFeedUseCase
import com.lookmyup.app.domain.usecase.RefreshFeedUseCase
import com.lookmyup.app.ui.TodayThemeUiState
import com.lookmyup.app.ui.UiState
import com.lookmyup.app.ui.feed.TodayThemeUiEvent
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
class TodayThemeViewModel @Inject constructor(
    //for simplicity, we are using Feed Use Cases
    private val getTodayThemePostUseCase: GetFeedUseCase,
    private val refreshTodayThemePostUseCase: RefreshFeedUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<TodayThemeUiState>(UiState.Loading())
    val uiState: StateFlow<TodayThemeUiState> get() = _uiState

    private val _uiEvents = MutableSharedFlow<TodayThemeUiEvent>()
    val uiEvents = _uiEvents.asSharedFlow()

    init {
        fetchTodayThemePosts()
        refreshFeed()
    }

    private fun refreshFeed() {
        _uiState.value = UiState.Loading()
        viewModelScope.launch {
            try {
                refreshTodayThemePostUseCase()
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Failed to refresh feed: ${e.message}")
            }
        }
    }

    private fun fetchTodayThemePosts() {
        viewModelScope.launch {
            getTodayThemePostUseCase()
                .catch { e ->
                    _uiState.value = UiState.Error(e.message ?: "An unexpected error occurred")
                }
                .collectLatest { posts ->
                    _uiState.value = UiState.Success(posts)
                }
        }
    }

    fun onCreatePostClicked() {
        viewModelScope.launch {
            _uiEvents.emit(TodayThemeUiEvent.NavigateToCreatePost)
        }
    }

    fun onStoryClicked(post: Post) {
        //TODO: Implement story click
    }
}