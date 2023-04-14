package com.vagif_tagiyev.newsfinalapp.api

import com.vagif_tagiyev.newsfinalapp.util.Constant.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object{
        private val newsInterceptor by lazy{
            val newsLogging = HttpLoggingInterceptor()
            newsLogging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(newsLogging)
                .build()
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        val api by lazy{
            newsInterceptor.create(NewsAPI::class.java)
        }
    }
}