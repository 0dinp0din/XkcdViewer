package com.example.xkdcviewer.models

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

data class Xkcd(
    val month: String,
    val num: Int,
    val link: String,
    val year: String,
    val news: String,
    val safe_title: String,
    val transcript: String,
    val alt: String,
    val img: String,
    val title: String,
    val day: String,
)

@Entity(tableName = "comics")
data class OfflineXkcd(
    @PrimaryKey
    val num: Int,
    val alt: String,
    val img: Bitmap,
    val title: String,
    val explanation: String,
)
