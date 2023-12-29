package com.example.xkdcviewer.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.xkdcviewer.data.viewmodels.RetrofitViewModel
import com.example.xkdcviewer.data.viewmodels.RoomViewModel

@Composable
fun MainScreen() {
    val roomViewModel = RoomViewModel(LocalContext.current)
    val retrofitViewModel = RetrofitViewModel()
    val navController = rememberNavController()

    val hasConnection by retrofitViewModel.hasConnection.collectAsState()

    NavHost(
        navController = navController,
        startDestination = if (hasConnection) "comicScreen" else "offlineScreen",
    ) {
        composable(
            route = "comicScreen",
        ) {
            ComicScreen(
                roomVm = roomViewModel,
                retrofitVm = retrofitViewModel,
                context = LocalContext.current,
            )
        }

        composable(
            route = "offlineScreen",
        ) {
            OfflineScreen(retrofitVm = retrofitViewModel, navController = navController)
        }

        composable(
            route = "offlineComicScreen/{comicNum}",
            arguments = listOf(navArgument("comicNum") { type = NavType.IntType }),
        ) { backStackEntry ->
            val comicNum = backStackEntry.arguments?.getInt("comicNum") ?: 0
            OfflineComicScreen(roomVm = roomViewModel, num = comicNum)
        }

        composable(
            route = "offlineListScreen",
        ) {
            OfflineComicListScreen(
                roomVm = roomViewModel,
                navController = navController,
            )
        }
    }
}
