package com.vagif_tagiyev.newsfinalapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.vagif_tagiyev.newsfinalapp.R
import com.vagif_tagiyev.newsfinalapp.database.NewsDatabase
import com.vagif_tagiyev.newsfinalapp.repository.NewsRepository
import com.vagif_tagiyev.newsfinalapp.ui.viewmodel.NewsProvider
import com.vagif_tagiyev.newsfinalapp.ui.viewmodel.NewsViewModel

class MainActivity : AppCompatActivity() {
    lateinit var newsModel : NewsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val newsRepository = NewsRepository(NewsDatabase(this))
        val newsModelProviderFactory = NewsProvider(application,newsRepository)
        newsModel = ViewModelProvider(this,newsModelProviderFactory)[NewsViewModel ::class.java]
        supportActionBar?.hide()
    }
}