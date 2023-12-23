package com.example.xkdcviewer.models

import com.google.gson.annotations.SerializedName

data class ComicExplanation(
    val parse: Parse,
)

data class Parse(
    val wikitext: Wikitext,
)

data class Wikitext(
    @SerializedName("*")
    val wikitextContent: String,
)
