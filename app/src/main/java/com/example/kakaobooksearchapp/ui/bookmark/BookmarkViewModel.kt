package com.example.kakaobooksearchapp.ui.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kakaobooksearchapp.base.BaseViewModel
import com.example.kakaobooksearchapp.data.repo.BookmarkRepository
import com.example.kakaobooksearchapp.room.BookMarkItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(private val bookmarkRepository: BookmarkRepository) :
    BaseViewModel() {
//    private val _items = MutableStateFlow<List<BookMarkItem>>(emptyList())
//    val items: MutableStateFlow<List<BookMarkItem>> = _items

    init {
        getFavoriteBooks()
    }

    fun getFavoriteBooks() {
        viewModelScope.launch(Dispatchers.IO) {
            val bookmarkList = bookmarkRepository.getFavoriteBooks()
            withContext(Dispatchers.Main) {
                onChangedViewState(BookmarkViewState.BookmarkResult(bookmarkList))
            }
        }


    }

}