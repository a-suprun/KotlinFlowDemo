package com.example.kotlinflowdemo.screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinflowdemo.databinding.ItemNewsBinding
import com.example.kotlinflowdemo.network.model.Article

class NewsAdapter(
    private val newsList: MutableList<Article>
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder =
        NewsViewHolder(ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = newsList[position]
        holder.binding.apply {
            tvTitle.text = news.title
            tvDescription.text = news.description
        }
    }

    fun updateNewsList(newNewsList: List<Article>) {
        newsList.clear()
        newsList.addAll(newNewsList)
        DiffUtil.calculateDiff(NewsDiffCallback(newsList, newNewsList)).dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int = newsList.size

    inner class NewsViewHolder(val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root)

    private class NewsDiffCallback(
        private val oldNewsList: List<Article>,
        private val newNewsList: List<Article>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldNewsList.size

        override fun getNewListSize(): Int = newNewsList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldNewsList[oldItemPosition].title == newNewsList[newItemPosition].title

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldNewsList[oldItemPosition].description == newNewsList[newItemPosition].description
                    && oldNewsList[oldItemPosition].content == newNewsList[newItemPosition].content
    }
}