package com.example.presenter.ui.search

import com.example.domain.model.KakaoBook
import com.example.presenter.base.ViewEvent
import com.example.presenter.base.ViewState

sealed class SearchViewState : ViewState {
    data class GetSearchResult(val list: List<KakaoBook>) : SearchViewState()
}


sealed interface SearchViewEvent : ViewEvent {
    data class AddBookItem(val item : KakaoBook) : SearchViewEvent
    data class DeleteBookItem(val item : KakaoBook) : SearchViewEvent
}