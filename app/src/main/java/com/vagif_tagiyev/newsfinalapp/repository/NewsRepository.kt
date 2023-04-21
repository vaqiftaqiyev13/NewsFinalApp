package com.vagif_tagiyev.newsfinalapp.repository

import com.vagif_tagiyev.newsfinalapp.api.RetrofitInstance
import com.vagif_tagiyev.newsfinalapp.database.NewsDatabase
import com.vagif_tagiyev.newsfinalapp.model.Article

class NewsRepository(val newsDatabase: NewsDatabase) {
    suspend fun getAllNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getTopHeadLines(countryCode, pageNumber)

    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api.searchArticles(searchQuery, pageNumber)

    suspend fun upsert(article: Article) = newsDatabase.getAllNews().upsert(article)

    fun getSavedNews() = newsDatabase.getAllNews().getAllNews()

    suspend fun deleteNews(article: Article) = newsDatabase.getAllNews().deleteNews(article)
}