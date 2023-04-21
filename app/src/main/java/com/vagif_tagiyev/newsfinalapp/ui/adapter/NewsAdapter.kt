package com.vagif_tagiyev.newsfinalapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vagif_tagiyev.newsfinalapp.R
import com.vagif_tagiyev.newsfinalapp.databinding.RowItemBinding
import com.vagif_tagiyev.newsfinalapp.model.Article

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsHolder>() {

    inner class NewsHolder(val rowItemBinding: RowItemBinding) :
        RecyclerView.ViewHolder(rowItemBinding.root)

    private val diffUtilCallBack = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }
    val differList = AsyncListDiffer(this, diffUtilCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val binding = RowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return NewsHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        val newsArticle = differList.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(newsArticle.urlToImage).into(holder.rowItemBinding.itemImage)
            holder.rowItemBinding.itemTitle.text = newsArticle.title
            holder.rowItemBinding.itemSource.text = newsArticle.source?.name
            holder.rowItemBinding.itemDate.text = newsArticle.publishedAt

            setOnClickListener {
                onItemClickListener?.let {
                    it(newsArticle)
                }
            }
        }

    }

    override fun getItemCount(): Int {
        return differList.currentList.size
    }


    private var onItemClickListener: ((Article) -> Unit)? = null

    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }
}