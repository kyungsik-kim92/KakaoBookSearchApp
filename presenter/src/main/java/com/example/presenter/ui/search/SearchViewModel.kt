package com.example.presenter.ui.search

import androidx.lifecycle.viewModelScope
import com.example.domain.model.KakaoBook
import com.example.domain.usecase.DeleteBookmarkUseCase
import com.example.domain.usecase.GetFavoriteBookmarkUseCase
import com.example.domain.usecase.GetKakaoSearchBooksUseCase
import com.example.domain.usecase.InsertBookmarkUseCase
import com.example.presenter.base.BaseViewModel
import com.example.presenter.ext.toKakaoBookmark
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
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

//    val isBookmarkStateFlow = MutableStateFlow(false)

    private val _latestAddedState = MutableStateFlow(false)
    val latestAddedState: MutableStateFlow<Boolean> = _latestAddedState


    val inputSearchStateFlow = MutableStateFlow("")

//    private val isCheckBookmark: (KakaoBook) -> Flow<Boolean> = { document ->
//        getFavoriteBookmarkUseCase().map {
//            it.any { bookmark -> bookmark.isbn == document.isbn }
//        }
//    }

    fun searchBooks() =
        inputSearchStateFlow.value.let { input ->
            val response = getKakaoSearchBooksUseCase(
                input,
                DEFAULT_SEARCH_SORT,
                DEFAULT_SEARCH_PAGE,
                DEFAULT_SEARCH_SIZE
            )
            response.map { result ->
                onChangedViewState(SearchViewState.GetSearchResult(result.list))
            }
        }.launchIn(viewModelScope)


//    fun addBookMark(item: KakaoBook) {
//        viewModelScope.launch(Dispatchers.IO) {
//            onChangedViewState(SearchViewState.AddBookmarkResult(item))
//            insertBookmarkUseCase.invoke(item.toKakaoBookmark())
//        }
//    }
//
//
//    fun deleteBookMark(item: KakaoBook) {
//        viewModelScope.launch(Dispatchers.IO) {
//            onChangedViewState(SearchViewState.DeleteBookmarkResult(item))
//            deleteBookmarkUseCase.invoke(item.toKakaoBookmark())
//        }
//    }

//
//    fun toggleBookmark(item: KakaoBook) = viewModelScope.launch(Dispatchers.IO) {
//        if (isCheckBookmark(item).first()) {
//            insertBookmarkUseCase(item.toKakaoBookmark())
//        } else {
//            deleteBookmarkUseCase(item.toKakaoBookmark())
//        }
//    }
//
//    private fun checkBookmarkState(item: KakaoBook) {
//        isCheckBookmark(item).onEach {
//            isBookmarkStateFlow.value = it
//        }.launchIn(viewModelScope)
//    }


//    fun loadSavedState() {
//        viewModelScope.launch {
//            val bookmarkItem = getFavoriteBookmarkUseCase()
//            latestAddedState.value = bookmarkItem != null
//        }
//
//    }

    fun addBookmark(item: KakaoBook) {
        val latest = _latestAddedState.value
        viewModelScope.launch {
            if (!latest)
                insertBookmarkUseCase.invoke(item.toKakaoBookmark())
            _latestAddedState.value = latest
        }
    }

    fun deleteBookmark(item: KakaoBook) {
        val latest = _latestAddedState.value
        viewModelScope.launch {
            if (latest)
                deleteBookmarkUseCase.invoke(item.toKakaoBookmark())
        }
        _latestAddedState.value = !latest
    }


    companion object {
        private const val DEFAULT_SEARCH_SORT = "accuracy"
        private const val DEFAULT_SEARCH_PAGE = 1
        private const val DEFAULT_SEARCH_SIZE = 30

    }
}