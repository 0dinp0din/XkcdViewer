package com.example.xkdcviewer.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.xkdcviewer.models.Xkcd

@Dao
interface ComicDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(comic: Xkcd)

    @Query("SELECT * FROM comics WHERE num = :comicNum")
    suspend fun getComic(comicNum: Int): Xkcd?

    @Query("SELECT * FROM comics")
    suspend fun getAllComics(): List<Xkcd>

    @Query("SELECT num FROM comics")
    suspend fun getFavourites(): List<Int>

    @Query("DELETE FROM comics WHERE num = :comicNum")
    suspend fun removeByNum(comicNum: Int)
}
