package com.anikulki.coroutineexample.ui.newslist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anikulki.coroutineexample.data.model.News
import com.anikulki.coroutineexample.data.repository.NewsListRepository
import com.anikulki.coroutineexample.utils.Resource
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel (private val newsListRepository: NewsListRepository): ViewModel() {

    private val newsList = MutableLiveData<Resource<List<News>>>()

    init {
        fetchNews()
    }

    private fun fetchNews(){
        viewModelScope.launch {
            newsList.postValue(Resource.loading(data = null))

            try {
                val response = newsListRepository.getNews()
                newsList.postValue(Resource.success(response))
            }catch (e: Exception){
                newsList.postValue(Resource.error(null, e.toString()))
                e.printStackTrace()
            }
        }
    }

    fun getNews(): LiveData<Resource<List<News>>> = newsList
}