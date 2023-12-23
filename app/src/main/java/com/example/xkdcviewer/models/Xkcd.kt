package com.example.xkdcviewer.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comics")
data class Xkcd(
    val month: String,
    @PrimaryKey
    val num: Int,
    val link: String,
    val year: String,
    val news: String,
    val safe_title: String,
    val transcript: String,
    val alt: String,
    val img: String,
    val title: String,
    val day: String
)
