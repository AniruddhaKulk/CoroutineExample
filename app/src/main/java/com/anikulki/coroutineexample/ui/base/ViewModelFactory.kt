package com.anikulki.coroutineexample.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anikulki.coroutineexample.data.api.ApiHelper
import com.anikulki.coroutineexample.data.repository.NewsListRepositoryImpl
import com.anikulki.coroutineexample.ui.newslist.viewmodel.MainViewModel
import com.anikulki.coroutineexample.ui.newslist.viewmodel.SeriesMainViewModel
import java.lang.IllegalStateException

class ViewModelFactory(private val apiHelper: ApiHelper): ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(NewsListRepositoryImpl(apiHelper)) as T
        }

        if(modelClass.isAssignableFrom(SeriesMainViewModel::class.java)){
            return SeriesMainViewModel(NewsListRepositoryImpl(apiHelper)) as T
        }

        throw IllegalStateException("Unknown class name")
    }


}