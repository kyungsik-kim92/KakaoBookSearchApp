package com.example.kakaobooksearchapp.ui.search

import com.example.domain.model.KakaoBook
import com.example.kakaobooksearchapp.base.ViewState


sealed class SearchViewState : ViewState {

    data class GetSearchResult(val list: List<KakaoBook>) : SearchViewState()
    data class AddBookmarkResult(val result: Boolean, val item: KakaoBook) : SearchViewState()
    data class DeleteBookmarkResult(val result: Boolean, val item: KakaoBook) :
        SearchViewState()
}