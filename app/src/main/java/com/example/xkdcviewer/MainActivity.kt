package com.example.xkdcviewer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.xkdcviewer.data.viewmodels.RetrofitViewModel
import com.example.xkdcviewer.data.viewmodels.RoomViewModel
import com.example.xkdcviewer.screens.ComicScreen
import com.example.xkdcviewer.ui.theme.XKDCViewerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            XKDCViewerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val roomViewModel = RoomViewModel(applicationContext)
                    val retrofitViewModel = RetrofitViewModel()

                    ComicScreen(roomViewModel, retrofitViewModel)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    XKDCViewerTheme {
    }
}
