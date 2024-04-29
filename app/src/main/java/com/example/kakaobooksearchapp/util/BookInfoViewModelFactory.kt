package com.example.kakaobooksearchapp.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.kakaobooksearchapp.ui.bookinfo.BookInfoViewModel
import com.example.kakaobooksearchapp.ui.bookmark.BookmarkRepository

//class BookInfoViewModelFactory(
//    private val bookmarkRepository: BookmarkRepository
//) :
//    androidx.lifecycle.ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>,extras: CreationExtras): T {
//        return BookInfoViewModel(extras.createSavedStateHandle(),bookmarkRepository) as T
//    }
//}