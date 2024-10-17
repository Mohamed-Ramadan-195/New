package com.example.anew.domain.usecases.news

import com.example.anew.domain.model.Article
import com.example.anew.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SelectArticles (
    private val newsRepository: NewsRepository
) {

    operator fun invoke(): Flow<List<Article>> {
        return newsRepository.selectArticles()
    }

}