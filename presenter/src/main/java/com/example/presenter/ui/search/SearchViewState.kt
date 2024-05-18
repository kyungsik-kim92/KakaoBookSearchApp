package com.example.presenter.ui.search

import com.example.domain.model.KakaoBook
import com.example.presenter.base.ViewEvent
import com.example.presenter.base.ViewState

sealed interface SearchViewEvent : ViewEvent {
    data class AddBookItem(val item: KakaoBook) : SearchViewEvent
    data class DeleteBookItem(val item: KakaoBook) : SearchViewEvent
}

data class SearchViewState(val list: List<KakaoBook>) : ViewState {

}