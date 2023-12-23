package com.example.xkdcviewer.components

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun CopyButton(num: String) {
    val context = LocalContext.current
    val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clipData = ClipData.newPlainText("Text", "https://xkcd.com/$num/")

    IconButton(
        onClick = {
            clipboardManager.setPrimaryClip(clipData)

            Toast.makeText(context, "Comic Url copied to clipboard", Toast.LENGTH_SHORT).show()
        },
        modifier = Modifier.padding(8.dp),
    ) {
        Icon(
            imageVector = Icons.Default.Share,
            contentDescription = "Copy Url",
        )
    }
}
