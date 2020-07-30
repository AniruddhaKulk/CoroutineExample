package com.anikulki.coroutineexample.ui.newslist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anikulki.coroutineexample.R
import com.anikulki.coroutineexample.data.model.News
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_layout.view.*

class NewsListAdapter(
    private val newsList: ArrayList<News>
) : RecyclerView.Adapter<NewsListAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(news: News) {
            itemView.textViewTitle.text = news.title
            itemView.textViewDescription.text = news.description
            itemView.textViewSource.text = news.source
            Glide.with(itemView.imageViewBanner.context)
                .load(news.imageUrl)
                .into(itemView.imageViewBanner)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout, parent,
                false
            )
        )

    override fun getItemCount(): Int = newsList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(newsList[position])

    fun addData(list: List<News>) {
        newsList.addAll(list)
    }

}