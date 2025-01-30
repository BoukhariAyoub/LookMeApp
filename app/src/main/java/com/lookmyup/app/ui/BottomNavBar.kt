package com.lookmyup.app.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun BottomNavBar(navController: NavController, currentRoute: String) {
    val items = listOf(
        BottomNavItem("feed", Icons.Filled.Home, "Feed"),
        BottomNavItem("search", Icons.Filled.Search, "Search"),
        BottomNavItem("create", Icons.Filled.AddCircle, "Create"),
        BottomNavItem("moodboard", Icons.Filled.Star, "MoodBoard"),
        BottomNavItem("profile", Icons.Filled.Person, "Profile")
    )

    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp),
        tonalElevation = 4.dp,
    ) {
        items.forEach { item ->
            val selected = currentRoute == item.route
            val color by animateColorAsState(
                targetValue = if (selected) MaterialTheme.colorScheme.primary else Color.Gray
            )

            NavigationBarItem(
                selected = selected,
                onClick = { navController.navigate(item.route) },
                icon = {
                    Icon(
                        modifier = Modifier.size(
                            if (selected) 24.dp else 20.dp
                        ),
                        imageVector = item.icon,
                        contentDescription = item.label,
                        tint = color
                    )
                },
            )
        }
    }
}

data class BottomNavItem(
    val route: String,
    val icon: ImageVector,
    val label: String
)