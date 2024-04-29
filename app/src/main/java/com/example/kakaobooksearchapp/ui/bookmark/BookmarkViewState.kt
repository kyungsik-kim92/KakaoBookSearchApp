package com.example.kakaobooksearchapp.ui.bookmark

import com.example.kakaobooksearchapp.base.ViewState
import com.example.kakaobooksearchapp.room.BookMarkItem

sealed class BookmarkViewState : ViewState {
    data class BookmarkResult(val list : List<BookMarkItem>): BookmarkViewState()
}