package com.example.xkdcviewer.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.xkdcviewer.models.OfflineXkcd

@Database(entities = [OfflineXkcd::class], version = 1, exportSchema = false)
@TypeConverters(BitmapTypeConverter::class)
abstract class ComicDatabase : RoomDatabase() {
    abstract fun comicDao(): ComicDao
}
