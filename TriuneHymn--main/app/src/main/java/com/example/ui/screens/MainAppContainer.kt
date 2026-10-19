package com.example.ui.screens

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ui.HymnViewModel

object Routes {
    const val SPLASH = "splash"
    const val HOME = "home"
    const val LIBRARY = "library"
    const val READER = "reader/{hymnId}"
    const val FAVORITES = "favorites"
    const val CATEGORIES = "categories"
    const val SETTINGS = "settings"
    const val ABOUT = "about"
    const val CONTACT = "contact"
    const val DONATE = "donate"
}

@Composable
fun MainAppContainer(
    viewModel: HymnViewModel = hiltViewModel()
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH
    ) {
        composable(Routes.SPLASH) {
            SplashScreen(
                onEnterHymnal = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.SPLASH) { inclusive = true }
                    }
                }
            )
        }
        composable(Routes.HOME) { HomeScreen() }
        composable(Routes.LIBRARY) { HymnLibraryScreen() }
        composable(Routes.FAVORITES) { FavoritesScreen() }
        composable(Routes.CATEGORIES) { CategoriesScreen() }
        composable(Routes.SETTINGS) { SettingsScreen() }
        composable(Routes.ABOUT) { AboutScreen() }
        composable(Routes.CONTACT) { ContactScreen() }
        composable(Routes.DONATE) { DonateScreen() }
        composable(Routes.READER) { HymnReaderScreen() }
    }
}
