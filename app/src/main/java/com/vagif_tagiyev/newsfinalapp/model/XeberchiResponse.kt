package com.vagif_tagiyev.newsfinalapp.model

data class XeberchiResponse(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
)