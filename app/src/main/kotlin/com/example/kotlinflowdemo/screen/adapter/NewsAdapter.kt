package com.example.kotlinflowdemo.screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinflowdemo.databinding.ItemNewsBinding
import com.example.kotlinflowdemo.network.Article

class NewsAdapter(
    private val newsList: List<Article>
): RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder =
        NewsViewHolder(ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = newsList[position]
        holder.binding.apply {
            tvTitle.text = news.title
            tvDescription.text = news.description
        }
    }

    override fun getItemCount(): Int = newsList.size

    inner class NewsViewHolder(val binding: ItemNewsBinding): RecyclerView.ViewHolder(binding.root)
}