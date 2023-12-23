package com.example.xkdcviewer.services

import com.example.xkdcviewer.models.ComicExplanation
import com.example.xkdcviewer.models.Xkcd
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface XkcdService {
    @GET("{comicId}/info.0.json")
    suspend fun getComicById(@Path("comicId") comicId: Int): Xkcd

    @GET("info.0.json")
    suspend fun getFirstComic(): Xkcd
}

interface XkcdExplainService {
    @GET("api.php")
    suspend fun getComicExplanation(
        @Query("action") action: String = "parse",
        @Query("page") page: String,
        @Query("prop") prop: String = "wikitext",
        @Query("sectiontitle") sectionTitle: String = "Explanation",
        @Query("format") format: String = "json",
    ): ComicExplanation
}
object RetrofitClient {

    val xkcdService: XkcdService by lazy {
        Retrofit.Builder()
            .baseUrl("https://xkcd.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(XkcdService::class.java)
    }

    val xkcdExplainService: XkcdExplainService by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.explainxkcd.com/wiki/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(XkcdExplainService::class.java)
    }
}
