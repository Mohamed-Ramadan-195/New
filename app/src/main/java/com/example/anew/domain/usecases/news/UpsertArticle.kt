package com.example.anew.domain.usecases.news

import com.example.anew.domain.model.Article
import com.example.anew.domain.repository.NewsRepository

class UpsertArticle (
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(article: Article) {
        newsRepository.upsertArticle(article)
    }

}