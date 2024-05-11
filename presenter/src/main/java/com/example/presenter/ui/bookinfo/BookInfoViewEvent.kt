package com.example.presenter.ui.bookinfo

import com.example.presenter.base.ViewEvent

sealed interface BookInfoViewEvent : ViewEvent {
    data object AddBookmark : BookInfoViewEvent
    data object DeleteBookmark : BookInfoViewEvent
}