package com.example.xkdcviewer.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ExplainButton(showAlert: () -> Unit) {
    IconButton(
        onClick = {
            showAlert()
        },
        modifier = Modifier.padding(8.dp),
    ) {
        Icon(
            imageVector = Icons.Default.Info,
            contentDescription = "Show explanation",
        )
    }
}
