package com.example.xkdcviewer.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.xkdcviewer.components.CopyButton
import com.example.xkdcviewer.components.ExplainButton
import com.example.xkdcviewer.components.InfoAlertDialog
import com.example.xkdcviewer.components.OfflineComicCard
import com.example.xkdcviewer.data.viewmodels.RoomViewModel

@Composable
fun OfflineComicScreen(
    roomVm: RoomViewModel,
    num: Int,
) {
    roomVm.getComicById(num)

    val comic by roomVm.currentComic.collectAsState()
    var showAlert by remember { mutableStateOf(false) }

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row {
            CopyButton(num = comic?.num.toString())

            ExplainButton(showAlert = { showAlert = true })

            if (showAlert) {
                InfoAlertDialog(comic!!.explanation, onDismiss = { showAlert = false })
            }
        }
        comic?.let { OfflineComicCard(comic = it) }
    }
}
