package com.example.kakaobooksearchapp.ui.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.kakaobooksearchapp.room.BookMarkItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(private val bookmarkRepository: BookmarkRepository) : ViewModel() {
    private val _items = MutableLiveData<List<BookMarkItem>>()
    val items: LiveData<List<BookMarkItem>> = _items


    init {
        getFavoriteBooks()
    }

     fun getFavoriteBooks() {
        viewModelScope.launch(Dispatchers.IO) {
            val bookmarkList = bookmarkRepository.getFavoriteBooks()

            withContext(Dispatchers.Main) {
                _items.value = bookmarkList
            }
        }


    }

}