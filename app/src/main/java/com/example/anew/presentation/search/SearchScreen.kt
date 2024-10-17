package com.example.anew.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.anew.domain.model.Article
import com.example.anew.presentation.Dimens.MediumPadding1
import com.example.anew.presentation.common.ArticleList
import com.example.anew.presentation.common.SearchBar

@Composable
fun SearchScreen (
    searchState: SearchState,
    searchEvent: (SearchEvent) -> Unit,
    navigateToDetails: (Article) -> Unit
) {
    Column (
        modifier = Modifier
            .padding (
                top = MediumPadding1,
                start = MediumPadding1,
                end = MediumPadding1
            )
            .statusBarsPadding()
            .fillMaxSize()
    ) {
        SearchBar (
            text = searchState.searchQuery,
            readOnly = false,
            onValueChange = { searchEvent(SearchEvent.UpdateSearchQuery(it)) },
            onSearch = { searchEvent(SearchEvent.SearchNews) }
        )
        Spacer (modifier = Modifier.height(MediumPadding1))
        searchState.articles?.let {
            val articles = it.collectAsLazyPagingItems()
            ArticleList (
                articles = articles,
                onClick = { navigateToDetails(it) }
            )
        }
    }
}