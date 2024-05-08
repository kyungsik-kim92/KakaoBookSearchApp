package com.example.kakaobooksearchapp.ui.bookmark

import com.example.kakaobooksearchapp.base.ViewState
import com.example.data.api.response.BookmarkItem
import com.example.domain.model.KakaoBookmark

sealed class BookmarkViewState : ViewState {
    data class BookmarkResult(val list : List<KakaoBookmark>): BookmarkViewState()
}