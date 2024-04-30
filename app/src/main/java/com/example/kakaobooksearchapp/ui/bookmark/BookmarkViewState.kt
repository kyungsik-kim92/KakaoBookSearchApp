package com.example.kakaobooksearchapp.ui.bookmark

import com.example.domain.model.KakaoBookmark
import com.example.kakaobooksearchapp.base.ViewState

sealed class BookmarkViewState : ViewState {
    data class BookmarkResult(val list: List<KakaoBookmark>) : BookmarkViewState()
}