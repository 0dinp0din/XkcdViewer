package com.example.xkdcviewer.screens

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.xkdcviewer.components.ComicCard
import com.example.xkdcviewer.components.CopyButton
import com.example.xkdcviewer.components.ExplainButton
import com.example.xkdcviewer.components.FavoriteButton
import com.example.xkdcviewer.components.InfoAlertDialog
import com.example.xkdcviewer.data.viewmodels.RetrofitViewModel
import com.example.xkdcviewer.data.viewmodels.RoomViewModel

@Composable
fun ComicScreen(
    roomVm: RoomViewModel,
    retrofitVm: RetrofitViewModel,
    context: Context,
) {
    var showAlert by remember { mutableStateOf(false) }
    val comic by retrofitVm.comic.collectAsState()
    val explanation by retrofitVm.explanation.collectAsState()
    val lastComicIndex by retrofitVm.lastComicIndex.collectAsState()

    Column {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
        ) {
            if ((comic?.num ?: 0) < lastComicIndex) {
                Button(onClick = { retrofitVm.getComic(comic!!.num + 1) }) {
                    Text(text = "Prev")
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            if ((comic?.num ?: 0) > 0) {
                Button(onClick = { retrofitVm.getComic(comic!!.num - 1) }) {
                    Text(text = "Next")
                }
            }
        }

        Row {
            FavoriteButton(isFavorite = (comic?.num in roomVm.favouriteNums.value), onClick = {
                comic?.num?.let { comicNum ->
                    if (comicNum in roomVm.favouriteNums.value) {
                        roomVm.deleteComic(comicNum)
                    } else {
                        roomVm.saveComic(comic!!, context, explanation)
                    }
                }
            })

            CopyButton(num = comic?.num.toString())

            ExplainButton(showAlert = { showAlert = true })

            if (showAlert) {
                InfoAlertDialog(explanation, onDismiss = { showAlert = false })
            }
        }
        comic?.let { ComicCard(comic = it) }
    }
}
