package com.example.xkdcviewer.data

import android.content.Context
import androidx.room.Room
import com.example.xkdcviewer.data.room.ComicDatabase
import com.example.xkdcviewer.models.OfflineXkcd

class ComicRepository(context: Context) {
    private val database =
        Room.databaseBuilder(
            context = context,
            ComicDatabase::class.java,
            "comic-database",
        ).build()

    suspend fun insertComic(comic: OfflineXkcd) {
        database.comicDao().insert(comic)
    }

    suspend fun getComicById(num: Int): OfflineXkcd? {
        return database.comicDao().getComic(num)
    }

    suspend fun getAllComics(): List<OfflineXkcd> {
        return database.comicDao().getAllComics()
    }

    suspend fun getFavouriteNums(): List<Int> {
        return database.comicDao().getFavourites()
    }

    suspend fun deleteComic(num: Int) {
        return database.comicDao().removeByNum(num)
    }
}
