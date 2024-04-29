package com.example.kakaobooksearchapp.ui.search

import com.example.kakaobooksearchapp.base.ViewState
import com.example.kakaobooksearchapp.network.response.KakaoBookItem

sealed class SearchViewState : ViewState {

    data class GetSearchResult(val list: List<KakaoBookItem>) : SearchViewState()
    data class AddBookmarkResult(val result: Boolean, val item: KakaoBookItem) : SearchViewState()
    data class DeleteBookmarkResult(val result: Boolean, val item: KakaoBookItem) :
        SearchViewState()
}