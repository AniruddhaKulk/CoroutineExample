package com.anikulki.coroutineexample.data.repository

import com.anikulki.coroutineexample.data.model.News

interface NewsListRepository {

    suspend fun getNews(): List<News>

    suspend fun getMoreNews(): List<News>
}