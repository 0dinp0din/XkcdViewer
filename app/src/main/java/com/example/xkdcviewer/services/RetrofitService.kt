package com.example.xkdcviewer.services

import com.example.xkdcviewer.models.Xkcd
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface XkcdService {
    @GET("{comicId}/info.0.json")
    suspend fun getComicById(@Path("comicId") comicId: Int): Xkcd

    @GET("info.0.json")
    suspend fun getFirstComic(): Xkcd
}


object RetrofitClient {
    private const val BASE_URL = "https://xkcd.com/"

    val xkcdService: XkcdService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(XkcdService::class.java)
    }
}