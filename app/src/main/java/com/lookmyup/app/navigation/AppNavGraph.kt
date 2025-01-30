package com.lookmyup.app.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lookmyup.app.ui.create.CreateScreen
import com.lookmyup.app.ui.feed.FeedScreen
import com.lookmyup.app.ui.moodboard.MoodBoardScreen
import com.lookmyup.app.ui.profile.ProfileScreen
import com.lookmyup.app.ui.search.SearchScreen

@Composable
fun AppNavGraph(navController: NavHostController, snackbarHostState: SnackbarHostState) {
    NavHost(navController = navController, startDestination = "feed") {
        composable("feed") {
            FeedScreen(
                snackbarHostState = snackbarHostState,
                navigateToCreatePost = {
                    navController.navigate("create")
                }
            )
        }
        composable("search") { SearchScreen() }
        composable("create") { CreateScreen() }
        composable("moodboard") { MoodBoardScreen() }
        composable("profile") { ProfileScreen() }
    }
}