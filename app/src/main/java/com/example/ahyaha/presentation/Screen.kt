package com.example.ahyaha.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Home : Screen("home", "HOME", Icons.Filled.Home)
    object Donors : Screen("donors", "ADD DONOR", Icons.Filled.Favorite)
    object Settings : Screen("settings", "SETTINGS", Icons.Filled.Settings)
}
