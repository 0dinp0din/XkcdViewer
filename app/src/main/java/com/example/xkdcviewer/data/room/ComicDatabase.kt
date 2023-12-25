package com.example.xkdcviewer.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.xkdcviewer.models.Xkcd

@Database(entities = [Xkcd::class], version = 1, exportSchema = false)
abstract class ComicDatabase : RoomDatabase() {
    abstract fun comicDao(): ComicDao
}
