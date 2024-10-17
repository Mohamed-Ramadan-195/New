package com.example.anew.domain.usecases.news

import com.example.anew.domain.model.Article
import com.example.anew.domain.repository.NewsRepository

class SelectArticle (
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(url: String): Article? {
        return newsRepository.selectArticle(url)
    }

}