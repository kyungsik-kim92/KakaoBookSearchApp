package com.example.kakaobooksearchapp.ui.search

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.kakaobooksearchapp.IsBookmark
import com.example.kakaobooksearchapp.ui.bookmark.BookmarkRepository
import com.example.kakaobooksearchapp.network.response.KakaoBookItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchRepository: SearchRepository,
    private val bookmarkRepository: BookmarkRepository
) : ViewModel() {

    private val _items = MutableLiveData<List<KakaoBookItem>>()
    val items: LiveData<List<KakaoBookItem>> = _items

    private val _bookMarkItems = MutableLiveData<IsBookmark>()
    val bookMarkItems: LiveData<IsBookmark> = _bookMarkItems


    val inputSearchLiveData = MutableLiveData("")

    fun searchBooks() = viewModelScope.launch(Dispatchers.IO) {
        inputSearchLiveData.value?.let { input ->

            val response = searchRepository.searchBooks(
                input,
                DEFAULT_SEARCH_SORT,
                DEFAULT_SEARCH_PAGE,
                DEFAULT_SEARCH_SIZE
            )
            if (response.isSuccessful) {

                val getBookmarkList = bookmarkRepository.getFavoriteBooks()
                response.body()?.let { body ->
                    val searchList = body.kakaoBookItems
                    searchList.map { searchItem ->
                        if (getBookmarkList.contains(searchItem.toBookmarkItem())) {
                            searchItem.isBookmark = true
                        }
                    }
                    _items.postValue(response.body()?.kakaoBookItems)
                }
            }
        }
    }


    fun addBookMark(item: KakaoBookItem) {
        viewModelScope.launch(Dispatchers.IO) {
            val addBookmarkResult = bookmarkRepository.insertBook(item.toBookmarkItem())
            _bookMarkItems.postValue(IsBookmark(addBookmarkResult >= 1L, item))
        }
    }


    fun deleteBookMark(item: KakaoBookItem) {
        viewModelScope.launch(Dispatchers.IO) {
            val deleteBookmarkResult = bookmarkRepository.deleteBook(item.toBookmarkItem())
            _bookMarkItems.postValue(IsBookmark(deleteBookmarkResult == 1, item))
        }
    }

    companion object {

        private const val DEFAULT_SEARCH_SORT = "accuracy"
        private const val DEFAULT_SEARCH_PAGE = 1
        private const val DEFAULT_SEARCH_SIZE = 30


        fun provideFactory(
            searchRepository: SearchRepository,
            bookmarkRepository: BookmarkRepository
        ) = viewModelFactory {
            initializer {
                SearchViewModel(searchRepository, bookmarkRepository)
            }
        }

    }


}