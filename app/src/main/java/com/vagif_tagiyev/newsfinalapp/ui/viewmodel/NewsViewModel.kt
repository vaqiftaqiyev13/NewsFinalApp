package com.vagif_tagiyev.newsfinalapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vagif_tagiyev.newsfinalapp.model.Article
import com.vagif_tagiyev.newsfinalapp.model.XeberchiResponse
import com.vagif_tagiyev.newsfinalapp.repository.NewsRepository
import com.vagif_tagiyev.newsfinalapp.util.NewsResponse
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(val newsRepository: NewsRepository) : ViewModel() {
    val topNews: MutableLiveData<NewsResponse<XeberchiResponse>> = MutableLiveData()
    var topNewsPage = 1

    val searchNews: MutableLiveData<NewsResponse<XeberchiResponse>> = MutableLiveData()
    var searchNewsPage = 1

    init {
        getAllNews("us")
    }

    fun getAllNews(countryCode: String) = viewModelScope.launch {
        topNews.postValue(NewsResponse.LoadResponse())
        val response = newsRepository.getAllNews(countryCode,topNewsPage)
        topNews.postValue(handleNewsResponse(response))
    }

    fun searchNews(searchQuery: String) = viewModelScope.launch {
        searchNews.postValue(NewsResponse.LoadResponse())
        val response = newsRepository.searchNews(searchQuery,topNewsPage)
        searchNews.postValue(handleNewsResponse(response))
    }

    private fun handleNewsResponse(newsResponse: Response<XeberchiResponse>) : NewsResponse<XeberchiResponse>{
        if (newsResponse.isSuccessful){
            newsResponse.body()?.let {
                return NewsResponse.SuccessResponse(it)
            }
        }
        return  NewsResponse.ErrorResponse(newsResponse.message())
    }


    private fun handleSearchResponse(newsResponse: Response<XeberchiResponse>) : NewsResponse<XeberchiResponse>{
        if (newsResponse.isSuccessful){
            newsResponse.body()?.let {
                return NewsResponse.SuccessResponse(it)
            }
        }
        return  NewsResponse.ErrorResponse(newsResponse.message())
    }

    fun saveNews(article: Article) = viewModelScope.launch {
        newsRepository.upsert(article)
    }

    fun getSavedNews() = newsRepository.getSavedNews()

    fun deleteNews(article: Article) = viewModelScope.launch {
        newsRepository.deleteNews(article)
    }

}