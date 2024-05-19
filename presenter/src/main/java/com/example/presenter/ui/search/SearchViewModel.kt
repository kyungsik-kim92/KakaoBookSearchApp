package com.example.presenter.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.mapper.toKakaoBookmark
import com.example.domain.model.KakaoBook
import com.example.domain.usecase.DeleteBookmarkUseCase
import com.example.domain.usecase.GetFavoriteBookmarkUseCase
import com.example.domain.usecase.GetKakaoSearchBooksUseCase
import com.example.domain.usecase.InsertBookmarkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getKakaoSearchBooksUseCase: GetKakaoSearchBooksUseCase,
    private val getFavoriteBookmarkUseCase: GetFavoriteBookmarkUseCase,
    private val insertBookmarkUseCase: InsertBookmarkUseCase,
    private val deleteBookmarkUseCase: DeleteBookmarkUseCase
) : ViewModel() {

    private val _searchViewState = MutableStateFlow(SearchViewState())
    val searchViewState: StateFlow<SearchViewState> = _searchViewState


    init {
        getFavoriteBookmarkUseCase().map {
            val bookmarkList = getFavoriteBookmarkUseCase().first()
            val convertBookmarkList = _searchViewState.value.list.map { item ->
                item.copy(isBookmark = bookmarkList.any { bookmark -> bookmark.isbn == item.isbn })
            }
            _searchViewState.update {
                it.copy(list = convertBookmarkList)
            }
        }.launchIn(viewModelScope)
    }


    fun searchBooks(keyword: String) =
        getKakaoSearchBooksUseCase(
            keyword,
            DEFAULT_SEARCH_SORT,
            DEFAULT_SEARCH_PAGE,
            DEFAULT_SEARCH_SIZE
        ).map { result ->
            val bookmarkList = getFavoriteBookmarkUseCase().first()
            val convertBookmarkList = result.list.map { item ->
                item.copy(isBookmark = bookmarkList.any { bookmark -> bookmark.isbn == item.isbn })
            }
            _searchViewState.update {
                it.copy(list = convertBookmarkList)
            }

        }.launchIn(viewModelScope)


    fun addBookmark(item: KakaoBook) = viewModelScope.launch(Dispatchers.IO) {
        insertBookmarkUseCase(item.toKakaoBookmark())
    }

    fun deleteBookmark(item: KakaoBook) = viewModelScope.launch(Dispatchers.IO) {
        deleteBookmarkUseCase(item.toKakaoBookmark())
    }


    companion object {
        private const val DEFAULT_SEARCH_SORT = "accuracy"
        private const val DEFAULT_SEARCH_PAGE = 1
        private const val DEFAULT_SEARCH_SIZE = 30

    }
}

