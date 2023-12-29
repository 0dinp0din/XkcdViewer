package com.example.xkdcviewer.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
        startDestination = if (hasConnection) "comicScreen" else "offlineScreen"
    ) {

        composable(
            route = "comicScreen"
        ) {
            ComicScreen(roomVm = roomViewModel, retrofitVm = retrofitViewModel)
        }

        composable(
            route = "offlineScreen"
        ) {
            OfflineScreen(retrofitVm = retrofitViewModel)
        }
    }
}