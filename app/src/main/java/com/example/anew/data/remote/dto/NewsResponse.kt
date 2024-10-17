package com.example.anew.data.remote.dto

import com.example.anew.domain.model.Article

data class NewsResponse (
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)