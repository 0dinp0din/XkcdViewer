package com.example.xkdcviewer.data.viewmodels

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xkdcviewer.data.ComicRepository
import com.example.xkdcviewer.models.Xkcd
import kotlinx.coroutines.launch

class RoomViewModel(context: Context) : ViewModel() {

    private val comicRepository = ComicRepository(context)

    val favouriteNums: MutableState<List<Int>> = mutableStateOf(emptyList())
    init {
        refreshComics()
    }

    fun saveComic(comic: Xkcd) {
        viewModelScope.launch {
            comicRepository.insertComic(comic)

            refreshComics()
        }
    }

    private fun refreshComics() {
        viewModelScope.launch {
            favouriteNums.value = comicRepository.getFavouriteNums()
        }
    }

    fun deleteComic(num: Int) {
        viewModelScope.launch {
            comicRepository.deleteComic(num)

            refreshComics()
        }
    }
}
