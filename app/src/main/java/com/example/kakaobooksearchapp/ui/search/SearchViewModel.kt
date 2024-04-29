package com.example.kakaobooksearchapp.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.kakaobooksearchapp.BookmarkResult
import com.example.kakaobooksearchapp.MainViewState
import com.example.kakaobooksearchapp.base.BaseViewModel
import com.example.kakaobooksearchapp.data.repo.SearchRepository
import com.example.kakaobooksearchapp.network.response.KakaoBookItem
import com.example.kakaobooksearchapp.data.repo.BookmarkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository,
    private val bookmarkRepository: BookmarkRepository
) : BaseViewModel() {


    val inputSearchStateFlow = MutableStateFlow("")

    fun searchBooks() = viewModelScope.launch(Dispatchers.IO) {
        inputSearchStateFlow.value.let { input ->

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
                    onChangedViewState(SearchViewState.GetSearchResult(body.kakaoBookItems))
                }
            }
        }
    }

    fun addBookMark(item: KakaoBookItem) {
        viewModelScope.launch(Dispatchers.IO) {
            val addBookmarkResult = bookmarkRepository.insertBook(item.toBookmarkItem())
            onChangedViewState(
                SearchViewState.AddBookmarkResult(
                    addBookmarkResult >= 1L,
                    item
                ),
            )
        }

    }


    fun deleteBookMark(item: KakaoBookItem) {
        viewModelScope.launch(Dispatchers.IO) {
            val deleteBookmarkResult = bookmarkRepository.deleteBook(item.toBookmarkItem())
            onChangedViewState(
                SearchViewState.DeleteBookmarkResult(
                    deleteBookmarkResult == 1,
                    item
                )
            )
        }
    }

    companion object {
        private const val DEFAULT_SEARCH_SORT = "accuracy"
        private const val DEFAULT_SEARCH_PAGE = 1
        private const val DEFAULT_SEARCH_SIZE = 30

    }
}