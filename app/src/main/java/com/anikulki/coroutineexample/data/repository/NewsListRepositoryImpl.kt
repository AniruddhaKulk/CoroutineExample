package com.anikulki.coroutineexample.data.repository

import com.anikulki.coroutineexample.data.api.ApiHelper
import com.anikulki.coroutineexample.data.model.News

class NewsListRepositoryImpl(private val apiHelper: ApiHelper): NewsListRepository {

    override suspend fun getNews() = apiHelper.getNews()

    override suspend fun getMoreNews() = apiHelper.getMoreNews()


}