package com.example.xkdcviewer.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.xkdcviewer.data.viewmodels.RoomViewModel
import com.example.xkdcviewer.models.OfflineXkcd

@Composable
fun OfflineComicListItem(
    comic: OfflineXkcd,
    roomVm: RoomViewModel,
    navController: NavController,
) {
    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable { navController.navigate("offlineComicScreen/${comic.num}") },
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    bitmap = comic.img.asImageBitmap(),
                    contentDescription = "Comic thumbnail",
                    modifier =
                        Modifier
                            .size(100.dp)
                            .padding(end = 16.dp),
                )

                Column {
                    Text(text = comic.title, fontWeight = FontWeight.Bold)
                    Text(text = "Number: ${comic.num}")
                }

                Column {
                    FavoriteButton(isFavorite = (comic.num in roomVm.favouriteNums.value), onClick = {
                        roomVm.deleteComic(comic.num)
                    })
                }
            }
        }
    }
}
