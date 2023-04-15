package com.vagif_tagiyev.newsfinalapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vagif_tagiyev.newsfinalapp.model.Source

@Entity(tableName = "news")
data class Article(
    @PrimaryKey(autoGenerate = true)
    val ID: Int? = null,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
)