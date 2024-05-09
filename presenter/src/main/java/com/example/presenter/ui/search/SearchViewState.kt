package com.example.presenter.ui.search

import com.example.domain.model.KakaoBook
import com.example.presenter.base.ViewState

sealed class SearchViewState : ViewState {

    data class GetSearchResult(val list: List<KakaoBook>) : SearchViewState()
    data class AddBookmarkResult(val item: KakaoBook) : SearchViewState()
    data class DeleteBookmarkResult(val item: KakaoBook) : SearchViewState()
    data class isBookmark(val item: KakaoBook) : SearchViewState()
}