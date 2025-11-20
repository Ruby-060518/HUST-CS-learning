package com.example.pwr.ui


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.compose.*
import com.example.pwr.ui.screens.DetailScreen
import com.example.pwr.ui.screens.HistoryScreen
import com.example.pwr.ui.screens.SearchScreen
import com.example.pwr.viewmodel.DetailViewModel
import com.example.pwr.viewmodel.HistoryViewModel
import com.example.pwr.viewmodel.SearchViewModel

object Destinations {
    const val SEARCH = "search"
    const val DETAIL = "detail"
    const val HISTORY = "history"
}

@Composable
fun MovieNavGraph(
    modifier: Modifier = Modifier,
    searchViewModel: SearchViewModel,
    detailViewModel: DetailViewModel,
    historyViewModel: HistoryViewModel,
    startDestination: String = Destinations.SEARCH,
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination, modifier = modifier) {
        composable(Destinations.SEARCH) {
            SearchScreen(
                viewModel = searchViewModel,
                onOpenDetail = { imdbId ->
                    navController.navigate("${Destinations.DETAIL}/$imdbId")
                },
                onOpenHistory = {
                    navController.navigate(Destinations.HISTORY)
                }
            )
        }
        composable(
            route = "${Destinations.DETAIL}/{imdbId}",
            arguments = listOf(navArgument("imdbId") { type = NavType.StringType })
        ) { backStackEntry ->
            val imdbId = backStackEntry.arguments?.getString("imdbId") ?: ""
            DetailScreen(imdbId = imdbId, viewModel = detailViewModel, onBack = { navController.popBackStack() })
        }
        composable(Destinations.HISTORY) {
            HistoryScreen(historyViewModel, onBack = { navController.popBackStack() }, onOpenDetail = { id ->
                navController.navigate("${Destinations.DETAIL}/$id")
            })
        }
    }
}
