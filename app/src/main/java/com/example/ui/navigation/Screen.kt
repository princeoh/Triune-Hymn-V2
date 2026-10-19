package com.example.ui.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Home : Screen("home")
    object Library : Screen("library")
    object Categories : Screen("categories")
    object HymnReader : Screen("hymn_reader/{hymnId}") {
        fun createRoute(hymnId: String) = "hymn_reader/$hymnId"
    }
    object Favorites : Screen("favorites")
    object Settings : Screen("settings")
    object About : Screen("about")
    object Donate : Screen("donate")
    object Contact : Screen("contact")
    object AdminEditor : Screen("admin_editor")
}

sealed class BottomNavItem(
    val route: String,
    val title: String,
    val iconName: String
) {
    object Home : BottomNavItem(Screen.Home.route, "Home", "home")
    object Library : BottomNavItem(Screen.Library.route, "Library", "library")
    object Categories : BottomNavItem(Screen.Categories.route, "Categories", "categories")
    object Favorites : BottomNavItem(Screen.Favorites.route, "Favorites", "favorites")
    object Settings : BottomNavItem(Screen.Settings.route, "Settings", "settings")
}
