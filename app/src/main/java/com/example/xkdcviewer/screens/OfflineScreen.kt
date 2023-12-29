package com.example.xkdcviewer.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import com.example.xkdcviewer.data.viewmodels.RetrofitViewModel

@Composable
fun OfflineScreen(
    retrofitVm: RetrofitViewModel,
    navController: NavController,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("You are offline")
        Button(onClick = { retrofitVm.getFirstComic() }) {
            Text("Refresh")
        }

        Button(onClick = {
            navController.navigate("offlineListScreen")
        }) {
            Text(text = "Browse saved comics")
        }
    }
}
