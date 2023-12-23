package com.example.xkdcviewer.data

import android.content.Context
import androidx.room.Room
import com.example.xkdcviewer.models.Xkcd

class ComicRepository(context: Context) {

    private val database = Room.databaseBuilder(
        context = context,
        ComicDatabase::class.java,
        "comic-database",
    ).build()
    suspend fun insertComic(comic: Xkcd) {
        database.comicDao().insert(comic)
    }
    suspend fun getComicById(num: Int): Xkcd? {
        return database.comicDao().getComic(num)
    }
    suspend fun getAllComics(): List<Xkcd> {
        return database.comicDao().getAllComics()
    }

    suspend fun getFavouriteNums(): List<Int> {
        return database.comicDao().getFavourites()
    }

    suspend fun deleteComic(num: Int) {
        return database.comicDao().removeByNum(num)
    }
}
