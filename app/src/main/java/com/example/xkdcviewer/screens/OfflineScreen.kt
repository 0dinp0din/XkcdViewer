package com.example.xkdcviewer.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.example.xkdcviewer.data.viewmodels.RetrofitViewModel

@Composable
fun OfflineScreen(retrofitVm: RetrofitViewModel) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("You are offline")
        Button(onClick = { retrofitVm.getFirstComic() }) {
            Text("Refresh")
        }
    }
}
