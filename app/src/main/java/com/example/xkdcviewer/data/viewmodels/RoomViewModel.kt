package com.example.xkdcviewer.data.viewmodels

import android.content.Context
import android.graphics.Bitmap
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.example.xkdcviewer.data.ComicRepository
import com.example.xkdcviewer.models.OfflineXkcd
import com.example.xkdcviewer.models.Xkcd
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RoomViewModel(context: Context) : ViewModel() {
    private val comicRepository = ComicRepository(context)

    val favouriteNums: MutableState<List<Int>> = mutableStateOf(emptyList())

    private val _savedComics = MutableStateFlow<List<OfflineXkcd>?>(null)
    val savedComics = _savedComics.asStateFlow()

    private val _currentComic = MutableStateFlow<OfflineXkcd?>(null)
    val currentComic = _currentComic.asStateFlow()

    init {
        refreshComics()
    }

    fun saveComic(
        comic: Xkcd,
        context: Context,
        explanation: String,
    ) {
        viewModelScope.launch {
            val bitmap: Bitmap? = loadBitmapFromUrl(comic.img, context)

            if (bitmap != null) {
                val saveComic =
                    OfflineXkcd(
                        num = comic.num,
                        alt = comic.alt,
                        img = bitmap,
                        title = comic.title,
                        explanation = explanation,
                    )
                comicRepository.insertComic(saveComic)
                refreshComics()
            }
        }
    }

    private suspend fun loadBitmapFromUrl(
        url: String,
        context: Context,
    ): Bitmap? =
        withContext(Dispatchers.IO) {
            try {
                Glide.with(context)
                    .asBitmap()
                    .load(url)
                    .submit()
                    .get()
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }

    fun getComicById(num: Int) {
        viewModelScope.launch {
            _currentComic.value = comicRepository.getComicById(num)
        }
    }

    private fun refreshComics() {
        viewModelScope.launch {
            favouriteNums.value = comicRepository.getFavouriteNums()
            _savedComics.value = comicRepository.getAllComics()
        }
    }

    fun deleteComic(num: Int) {
        viewModelScope.launch {
            comicRepository.deleteComic(num)

            refreshComics()
        }
    }
}
