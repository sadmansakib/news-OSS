package com.sadmansakib.newstime.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sadmansakib.newstime.R
import com.sadmansakib.newstime.models.Article
import kotlinx.android.synthetic.main.news_layout.view.*


class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.NewsViewHolder {
        return NewsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.news_layout,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
       return differ.currentList.size
    }

    override fun onBindViewHolder(holder: NewsAdapter.NewsViewHolder, position: Int) {
        val news = differ.currentList[position]

        holder.itemView.apply {
            Glide.with(this).asBitmap().apply(RequestOptions.overrideOf(400,400)).load(news.urlToImage).into(ivArticleImage)
            news_source.text = news.source.name
            news_title.text = news.title
            news_description.text = news.description
            news_publishedAt.text = news.publishedAt
        }
    }

    inner class NewsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}