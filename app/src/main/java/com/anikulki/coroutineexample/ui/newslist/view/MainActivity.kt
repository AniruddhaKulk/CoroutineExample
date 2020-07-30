package com.anikulki.coroutineexample.ui.newslist.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anikulki.coroutineexample.R
import com.anikulki.coroutineexample.data.api.ApiHelperImpl
import com.anikulki.coroutineexample.data.api.RetrofitBuilder
import com.anikulki.coroutineexample.data.model.News
import com.anikulki.coroutineexample.ui.base.ViewModelFactory
import com.anikulki.coroutineexample.ui.newslist.adapter.NewsListAdapter
import com.anikulki.coroutineexample.ui.newslist.viewmodel.MainViewModel
import com.anikulki.coroutineexample.ui.newslist.viewmodel.ParallelMainViewModel
import com.anikulki.coroutineexample.ui.newslist.viewmodel.SeriesMainViewModel
import com.anikulki.coroutineexample.utils.Status
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: ParallelMainViewModel
    private lateinit var adapter: NewsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        adapter = NewsListAdapter(arrayListOf())

        recyclerView.addItemDecoration(
            DividerItemDecoration(recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation)
        )

        recyclerView.adapter = adapter

        setupViewModel()

        setupObserver()
    }

    private fun setupViewModel() {
        mainViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelperImpl(RetrofitBuilder.apiService))
        ).get(ParallelMainViewModel::class.java)
    }


    private fun setupObserver() {
        mainViewModel.getNews().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    it.data?.let { newsList -> renderList(newsList) }
                    recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }


    private fun renderList(newsList: List<News>) {
        adapter.addData(newsList)
        adapter.notifyDataSetChanged()
    }
}