package com.vagif_tagiyev.newsfinalapp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vagif_tagiyev.newsfinalapp.repository.NewsRepository

class NewsProvider(val newsApp: Application,val newsRepository: NewsRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(newsApp, newsRepository) as T
    }
}