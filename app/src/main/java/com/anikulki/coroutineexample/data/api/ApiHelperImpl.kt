package com.anikulki.coroutineexample.data.api

import com.anikulki.coroutineexample.data.model.News

class ApiHelperImpl(private val apiService: ApiService): ApiHelper{

    override suspend fun getNews() = apiService.getNews()

    override suspend fun getMoreNews() = apiService.getMoreNews()
}

