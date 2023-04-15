package com.vagif_tagiyev.newsfinalapp.api

import com.vagif_tagiyev.newsfinalapp.model.XeberchiResponse
import com.vagif_tagiyev.newsfinalapp.util.Constant.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("v2/top-headlines")
    suspend fun getTopHeadLines(
        @Query("country")
        countryCode: String = "tr",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<XeberchiResponse>


    @GET("v2/everything")
    suspend fun searchArticles(
        @Query("q")
        searchQuery: String,
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<XeberchiResponse>


}