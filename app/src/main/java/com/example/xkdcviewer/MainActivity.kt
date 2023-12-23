package com.example.xkdcviewer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.xkdcviewer.models.Xkcd
import com.example.xkdcviewer.services.RetrofitClient
import com.example.xkdcviewer.ui.theme.XKDCViewerTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            XKDCViewerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FetchComicScreen()
                }
            }
        }
    }
}

@Composable
fun FetchComicScreen() {

    var xkcdComic by remember { mutableStateOf<Xkcd?>(null) }
    val comicApi = RetrofitClient.xkcdService
    val coroutineScope = rememberCoroutineScope()

    Column {
        Button(onClick = {
            coroutineScope.launch {
                xkcdComic = comicApi.getComicById(120)
            }
        }) {
            Text("Get comic by id")
        }

        Button(onClick = {
            coroutineScope.launch {
                xkcdComic = comicApi.getFirstComic()
            }
        }) {
            Text("Get newest comic")
        }

        xkcdComic?.let { Text(it.title) }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    XKDCViewerTheme {

    }
}