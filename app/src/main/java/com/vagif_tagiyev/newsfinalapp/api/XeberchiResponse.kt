package com.vagif_tagiyev.newsfinalapp.api

data class XeberchiResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)