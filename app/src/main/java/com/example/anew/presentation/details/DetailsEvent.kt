package com.example.anew.presentation.details

import com.example.anew.domain.model.Article

sealed class DetailsEvent {

    data class UpsertDeleteArticle(val article: Article): DetailsEvent()
    object RemoveSideEffect: DetailsEvent()

}