package com.example.presenter.ui.bookmark

import com.example.domain.model.KakaoBookmark
import com.example.presenter.base.ViewEvent

sealed class BookmarkViewEvent : ViewEvent {
    data class BookmarkResult(val list : List<KakaoBookmark>): BookmarkViewEvent()
}