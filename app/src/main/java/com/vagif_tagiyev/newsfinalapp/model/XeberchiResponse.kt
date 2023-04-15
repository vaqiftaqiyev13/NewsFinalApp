package com.vagif_tagiyev.newsfinalapp.model

data class XeberchiResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)