package com.example.presenter.ui.bookinfo

import com.example.domain.model.KakaoBookmark
import com.example.presenter.base.ViewState

sealed class BookInfoViewState : ViewState {

    data class AddBookmarkResult(val result: Boolean, val item: KakaoBookmark) : BookInfoViewState()

    data class DeleteBookmarkResult(val result: Boolean, val item: KakaoBookmark) :
        BookInfoViewState()

}