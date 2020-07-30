package com.anikulki.coroutineexample.data.api

import com.anikulki.coroutineexample.data.model.News

interface ApiHelper {

    suspend fun getNews(): List<News>

    suspend fun getMoreNews(): List<News>
}