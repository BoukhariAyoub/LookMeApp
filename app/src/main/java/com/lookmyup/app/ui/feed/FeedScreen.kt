package com.lookmyup.app.ui.feed

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.lookmyup.app.domain.entities.Post
import com.lookmyup.app.ui.FeedUiState
import com.lookmyup.app.ui.TodayThemeUiState
import com.lookmyup.app.ui.feed.posts.FeedListSection
import com.lookmyup.app.ui.feed.posts.FeedViewModel
import com.lookmyup.app.ui.feed.todaytheme.TodayThemeSection
import com.lookmyup.app.ui.feed.todaytheme.TodayThemeViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun FeedScreen(
    feedViewModel: FeedViewModel = hiltViewModel(),
    todayThemeViewModel: TodayThemeViewModel = hiltViewModel(),
    navigateToCreatePost: () -> Unit,
    snackbarHostState: SnackbarHostState
) {
    val feedUiState by feedViewModel.uiState.collectAsState()
    val todayThemeUiState by todayThemeViewModel.uiState.collectAsState()


    // Collect UI events and perform actions
    LaunchedEffect(feedViewModel.uiEvents) {
        feedViewModel.uiEvents.collectLatest { event ->
            when (event) {
                is FeedUiEvent.ShowErrorMessage -> {
                    snackbarHostState.showSnackbar(event.message)
                }
            }
        }
    }

    LaunchedEffect(todayThemeViewModel.uiEvents) {
        todayThemeViewModel.uiEvents.collectLatest { event ->
            when (event) {
                TodayThemeUiEvent.NavigateToCreatePost -> {
                    navigateToCreatePost()
                }
            }
        }
    }

    ScreenContent(
        modifier = Modifier.fillMaxSize(),
        feedUiState = feedUiState,
        todayThemeUiState = todayThemeUiState,
        onLikeClicked = feedViewModel::onLikeClicked,
        onSaveClicked = feedViewModel::onSaveClicked,
        onCommentClicked = feedViewModel::onCommentClicked,
        onShareClicked = feedViewModel::onShareClicked,
        onRemixClicked = feedViewModel::onRemixClicked,
        onCreatePostClicked = todayThemeViewModel::onCreatePostClicked,
        onStoryClicked = todayThemeViewModel::onStoryClicked
    )
}

@Composable
fun ScreenContent(
    modifier: Modifier = Modifier,
    feedUiState: FeedUiState,
    todayThemeUiState: TodayThemeUiState,
    onLikeClicked: (Post) -> Unit,
    onSaveClicked: (Post) -> Unit,
    onCommentClicked: (Post) -> Unit,
    onShareClicked: (Post) -> Unit,
    onRemixClicked: (Post) -> Unit,
    onCreatePostClicked: () -> Unit,
    onStoryClicked: (Post) -> Unit
) {
    Column(modifier = modifier) {
        TodayThemeSection(
            uiState = todayThemeUiState,
            onStoryClicked = onStoryClicked,
            onAddPostClicked = onCreatePostClicked
        )

        FeedListSection(
            uiState = feedUiState,
            onLikeClicked = onLikeClicked,
            onSaveClicked = onSaveClicked,
            onCommentClicked = onCommentClicked,
            onShareClicked = onShareClicked,
            onRemixClicked = onRemixClicked
        )
    }
}


