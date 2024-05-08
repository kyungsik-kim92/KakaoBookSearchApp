package com.example.kakaobooksearchapp.ui.bookinfo

import com.example.domain.model.KakaoBook
import com.example.kakaobooksearchapp.base.ViewState

sealed class BookInfoViewState : ViewState {

    data class AddBookmarkResult(val result: Boolean, val item: KakaoBook) : BookInfoViewState()

    data class DeleteBookmarkResult(val result: Boolean, val item: KakaoBook) :
        BookInfoViewState()

}