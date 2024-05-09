package com.example.kakaobooksearchapp.ui.bookmark

import com.example.kakaobooksearchapp.base.ViewState
import com.example.domain.model.KakaoBookmark
import com.example.kakaobooksearchapp.base.ViewEvent

sealed class BookmarkViewEvent : ViewEvent {
    data class BookmarkResult(val list : List<KakaoBookmark>): BookmarkViewEvent()
}