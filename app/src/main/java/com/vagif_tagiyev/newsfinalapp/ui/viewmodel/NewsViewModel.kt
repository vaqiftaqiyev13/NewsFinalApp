package com.vagif_tagiyev.newsfinalapp.ui.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.*
import android.net.NetworkCapabilities.*
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vagif_tagiyev.newsfinalapp.XeberchiApplication
import com.vagif_tagiyev.newsfinalapp.model.Article
import com.vagif_tagiyev.newsfinalapp.model.XeberchiResponse
import com.vagif_tagiyev.newsfinalapp.repository.NewsRepository
import com.vagif_tagiyev.newsfinalapp.util.NewsResponse
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import kotlin.reflect.typeOf

class NewsViewModel(newsApp: Application, val newsRepository: NewsRepository) :
    AndroidViewModel(newsApp) {

    val topNews: MutableLiveData<NewsResponse<XeberchiResponse>> = MutableLiveData()
    var topNewsPage = 1
    var newsRespo: XeberchiResponse? = null

    val searchNews: MutableLiveData<NewsResponse<XeberchiResponse>> = MutableLiveData()
    var searchNewsPage = 1
    var searchResponse: XeberchiResponse? = null

    init {
        getAllNews("us")
    }

    fun getAllNews(countryCode: String) = viewModelScope.launch {
        safeBreakingNewsCall(countryCode)
    }

    fun searchNews(searchQuery: String) = viewModelScope.launch {
        safeSearchNewsCall(searchQuery)
    }

    private fun handleNewsResponse(newsResponse: Response<XeberchiResponse>): NewsResponse<XeberchiResponse> {
        if (newsResponse.isSuccessful) {
            newsResponse.body()?.let {
                topNewsPage++
                if (newsRespo == null) {
                    newsRespo = it
                } else {
                    val oldNews = newsRespo?.articles
                    val newNews = it.articles
                    oldNews?.addAll(newNews)
                }

                return NewsResponse.SuccessResponse(newsRespo ?: it)
            }
        }
        return NewsResponse.ErrorResponse(newsResponse.message())
    }


    private fun handleSearchResponse(newsResponse: Response<XeberchiResponse>): NewsResponse<XeberchiResponse> {
        if (newsResponse.isSuccessful) {
            newsResponse.body()?.let {
                searchNewsPage++
                if (searchResponse == null) {
                    searchResponse = it
                } else {
                    val oldNews = searchResponse?.articles
                    val newNews = it.articles
                    oldNews?.addAll(newNews)
                }

                return NewsResponse.SuccessResponse(searchResponse ?: it)
            }
        }
        return NewsResponse.ErrorResponse(newsResponse.message())
    }

    fun saveNews(article: Article) = viewModelScope.launch {
        newsRepository.upsert(article)
    }

    fun getSavedNews() = newsRepository.getSavedNews()

    fun deleteNews(article: Article) = viewModelScope.launch {
        newsRepository.deleteNews(article)
    }

    private suspend fun safeBreakingNewsCall(countryCode: String) {
        topNews.postValue(NewsResponse.LoadResponse())
        try {
            if(checkInternetConnection()) {
                val response = newsRepository.getAllNews(countryCode, topNewsPage)
                topNews.postValue(handleNewsResponse(response))
            } else {
                topNews.postValue(NewsResponse.ErrorResponse("No internet connection"))
            }
        } catch(t: Throwable) {
            when(t) {
                is IOException -> topNews.postValue(NewsResponse.ErrorResponse("Network Fail"))
                else -> topNews.postValue(NewsResponse.ErrorResponse("Conversion Error"))
            }
        }
    }

    private suspend fun safeSearchNewsCall(searchQuery: String) {
        searchNews.postValue(NewsResponse.LoadResponse())
        try {
            if(checkInternetConnection()) {
                val response = newsRepository.searchNews(searchQuery, searchNewsPage)
                searchNews.postValue(handleSearchResponse(response))
            } else {
                searchNews.postValue(NewsResponse.ErrorResponse("No internet connection"))
            }
        } catch(t: Throwable) {
            when(t) {
                is IOException -> searchNews.postValue(NewsResponse.ErrorResponse("Network Fail"))
                else -> searchNews.postValue(NewsResponse.ErrorResponse("Conversion Error"))
            }
        }
    }
    private fun checkInternetConnection(): Boolean {
        val connectivityManager = getApplication<XeberchiApplication>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when{
                capabilities.hasTransport(TRANSPORT_WIFI) -> true
                capabilities.hasTransport(TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(TRANSPORT_ETHERNET) -> true
                else -> false

            }
        }
        return false

    }
}