package com.example.xkdcviewer.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.example.xkdcviewer.components.OfflineComicListItem
import com.example.xkdcviewer.data.viewmodels.RoomViewModel

@Composable
fun OfflineComicListScreen(
    roomVm: RoomViewModel,
    navController: NavController,
) {
    val savedComics by roomVm.savedComics.collectAsState()

    LazyColumn {
        if (savedComics?.isNotEmpty()!!) {
            items(savedComics!!) { comic ->
                OfflineComicListItem(
                    comic = comic,
                    roomVm = roomVm,
                    navController = navController,
                )
            }
        } else {
            item {
                Text(text = "No saved comics.. :(")
            }
        }
    }
}
