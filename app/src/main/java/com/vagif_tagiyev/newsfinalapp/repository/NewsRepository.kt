package com.vagif_tagiyev.newsfinalapp.repository

import com.vagif_tagiyev.newsfinalapp.api.RetrofitInstance
import com.vagif_tagiyev.newsfinalapp.database.NewsDatabase

class NewsRepository(val newsDatabase: NewsDatabase) {
    suspend fun getAllNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getTopHeadLines(countryCode, pageNumber)

    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api.searchArticles(searchQuery, pageNumber)
}