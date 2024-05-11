package com.example.presenter.ui.search

import androidx.lifecycle.viewModelScope
import com.example.data.mapper.toKakaoBookmark
import com.example.domain.model.KakaoBook
import com.example.domain.usecase.DeleteBookmarkUseCase
import com.example.domain.usecase.GetFavoriteBookmarkUseCase
import com.example.domain.usecase.GetKakaoSearchBooksUseCase
import com.example.domain.usecase.InsertBookmarkUseCase
import com.example.presenter.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getKakaoSearchBooksUseCase: GetKakaoSearchBooksUseCase,
    private val getFavoriteBookmarkUseCase: GetFavoriteBookmarkUseCase,
    private val insertBookmarkUseCase: InsertBookmarkUseCase,
    private val deleteBookmarkUseCase: DeleteBookmarkUseCase
) : BaseViewModel() {


    val inputSearchStateFlow = MutableStateFlow("")


    fun searchBooks() =
        inputSearchStateFlow.value.let { input ->
            getKakaoSearchBooksUseCase(
                input,
                DEFAULT_SEARCH_SORT,
                DEFAULT_SEARCH_PAGE,
                DEFAULT_SEARCH_SIZE
            ).map { result ->
                val bookmarkList = getFavoriteBookmarkUseCase().first()
                val convertBookmarkList = result.list.map { item ->
                    item.copy(isBookmark = bookmarkList.any { bookmark -> bookmark.isbn == item.isbn })
                }
                onChangedViewState(SearchViewState.GetSearchResult(convertBookmarkList))
            }.launchIn(viewModelScope)
        }


    fun addBookmark(item: KakaoBook) {
        viewModelScope.launch(Dispatchers.IO) {
            insertBookmarkUseCase(item.toKakaoBookmark())
            onChangedViewEvent(SearchViewEvent.AddBookItem(item))
        }
    }

    fun deleteBookmark(item: KakaoBook) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteBookmarkUseCase(item.toKakaoBookmark())
            onChangedViewEvent(SearchViewEvent.DeleteBookItem(item))
        }
    }


    companion object {
        private const val DEFAULT_SEARCH_SORT = "accuracy"
        private const val DEFAULT_SEARCH_PAGE = 1
        private const val DEFAULT_SEARCH_SIZE = 30

    }
}