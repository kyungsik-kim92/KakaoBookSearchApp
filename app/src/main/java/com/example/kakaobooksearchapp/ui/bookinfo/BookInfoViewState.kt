package com.example.kakaobooksearchapp.ui.bookinfo

import com.example.kakaobooksearchapp.base.ViewState
import com.example.data.api.response.KakaoBookItem
import com.example.domain.model.KakaoBook

sealed class BookInfoViewState : ViewState {

    data class AddBookmarkResult(val result: Boolean, val item: KakaoBook) : BookInfoViewState()

    data class DeleteBookmarkResult(val result: Boolean, val item: KakaoBook) :
        BookInfoViewState()

}