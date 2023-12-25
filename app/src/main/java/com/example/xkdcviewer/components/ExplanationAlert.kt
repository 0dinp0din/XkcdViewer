package com.example.xkdcviewer.components

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun InfoAlertDialog(explanation: String, onDismiss: () -> Unit) {
    AlertDialog(
        { onDismiss() },
        {
            Button(
                onClick = { onDismiss() },
                content = { Text("Close") },
            )
        },
        title = { Text("Explanation") },
        text = {
            Text(
                text = explanation,
                modifier = Modifier
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState()),
            )
        },
    )
}
