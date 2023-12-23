package com.example.xkdcviewer.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.xkdcviewer.components.ComicCard
import com.example.xkdcviewer.models.Xkcd
import com.example.xkdcviewer.services.RetrofitClient
import kotlinx.coroutines.launch

@Composable
fun ComicScreen() {
    var xkcdComic by remember { mutableStateOf<Xkcd?>(null) }
    val comicApi = RetrofitClient.xkcdService
    val coroutineScope = rememberCoroutineScope()
    var lastComicIndex by remember { mutableIntStateOf(0) }

    LaunchedEffect(key1 = Unit) {
        xkcdComic = comicApi.getFirstComic()
        lastComicIndex = xkcdComic!!.num ?: 0
    }

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            if ((xkcdComic?.num ?: 0) < lastComicIndex) {
                Button(onClick = {
                    coroutineScope.launch {
                        xkcdComic = xkcdComic?.let { comicApi.getComicById(it.num + 1) }
                    }
                }) {
                    Text(text = "Prev")
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            if ((xkcdComic?.num ?: 0) > 0) {
                Button(onClick = {
                    coroutineScope.launch {
                        xkcdComic = xkcdComic?.let { comicApi.getComicById(it.num - 1) }
                    }
                }) {
                    Text(text = "Next")
                }
            }
        }

        xkcdComic?.let { ComicCard(comic = it) }
    }
}
