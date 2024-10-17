package com.example.anew.presentation.bookmark

import com.example.anew.domain.model.Article

data class BookmarkState (
    val articles: List<Article> = emptyList()
)