package com.example.xkdcviewer.services

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xkdcviewer.data.ComicRepository
import com.example.xkdcviewer.models.Xkcd
import kotlinx.coroutines.launch

class ComicViewModel(context: Context) : ViewModel() {

    private val comicRepository = ComicRepository(context)
    private val explanationApi = RetrofitClient.xkcdExplainService

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

    suspend fun getExplanation(num: Int, title: String): String {
        val comic = explanationApi.getComicExplanation(page = "$num:_$title").parse.wikitext.wikitextContent

        val explanationStartIndex = comic.indexOf("==Explanation==") + "==Explanation==".length
        val transcriptStartIndex = comic.indexOf("==Transcript==")

        if (explanationStartIndex in 0..<transcriptStartIndex) {
            return comic.substring(explanationStartIndex, transcriptStartIndex).trim()
        }
        return "No explanation available"
    }
}
