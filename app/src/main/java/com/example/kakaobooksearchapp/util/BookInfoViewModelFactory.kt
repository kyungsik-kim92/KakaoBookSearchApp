package com.example.kakaobooksearchapp.util

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.kakaobooksearchapp.ui.bookinfo.BookInfoViewModel
import com.example.kakaobooksearchapp.ui.bookmark.BookmarkRepository

class BookInfoViewModelFactory(
    private val savedStateHandle: SavedStateHandle,
    private val bookMarkRepository: BookmarkRepository
) :
    androidx.lifecycle.ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BookInfoViewModel(savedStateHandle,bookMarkRepository) as T
    }
}