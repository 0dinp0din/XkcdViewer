package com.example.xkdcviewer.data.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xkdcviewer.data.RetrofitClient
import com.example.xkdcviewer.models.Xkcd
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RetrofitViewModel : ViewModel() {
    private val retrofit = RetrofitClient

    private val _comic = MutableStateFlow<Xkcd?>(null)
    val comic = _comic.asStateFlow()

    private val _explanation = MutableStateFlow("")
    val explanation = _explanation.asStateFlow()

    private val _lastComicIndex = MutableStateFlow(0)
    val lastComicIndex = _lastComicIndex.asStateFlow()

    private val _hasConnection = MutableStateFlow(true)
    val hasConnection = _hasConnection.asStateFlow()

    init {
        getFirstComic()
    }

    fun getFirstComic() {
        viewModelScope.launch {
            try {
                _comic.value = retrofit.xkcdService.getFirstComic()
                getExplanation()
                _lastComicIndex.value = comic.value?.num ?: 0

                _hasConnection.value = true
            } catch (e: Exception) {
                _hasConnection.value = false
            }
        }
    }

    fun getComic(num: Int) {
        viewModelScope.launch {
            try {
                _comic.value = retrofit.xkcdService.getComicById(num)
                getExplanation()

                _hasConnection.value = true
            } catch (e: Exception) {
                _hasConnection.value = false
            }
        }
    }

    private fun getExplanation() {
        viewModelScope.launch {
            val rawExplanation =
                retrofit.xkcdExplainService.getComicExplanation(
                    page = "${comic.value?.num}:_${comic.value?.title}",
                ).parse.wikitext.wikitextContent

            val explanationStartIndex = rawExplanation.indexOf("==Explanation==") + "==Explanation==".length
            val transcriptStartIndex = rawExplanation.indexOf("==Transcript==")

            if (explanationStartIndex in 0..<transcriptStartIndex) {
                _explanation.value = rawExplanation.substring(explanationStartIndex, transcriptStartIndex).trim()
            }
        }
    }
}
