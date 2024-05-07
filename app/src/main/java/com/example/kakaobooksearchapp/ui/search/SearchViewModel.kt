package com.example.kakaobooksearchapp.ui.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.data.mapper.toBookmarkItem
import com.example.data.mapper.toKakaoBookmark
import com.example.domain.model.KakaoBook
import com.example.domain.usecase.DeleteBookmarkUseCase
import com.example.domain.usecase.GetFavoriteBookUseCase
import com.example.domain.usecase.GetKakaoSearchBooksUseCase
import com.example.domain.usecase.InsertBookmarkUseCase
import com.example.kakaobooksearchapp.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getKakaoSearchBooksUseCase: GetKakaoSearchBooksUseCase,
    private val getFavoriteBookUseCase: GetFavoriteBookUseCase,
    private val insertBookmarkUseCase: InsertBookmarkUseCase,
    private val deleteBookmarkUseCase: DeleteBookmarkUseCase
) : BaseViewModel() {


    val inputSearchStateFlow = MutableStateFlow("")

    fun searchBooks() = viewModelScope.launch(Dispatchers.IO) {
        inputSearchStateFlow.value.let { input ->

//            val response = getKakaoSearchBooksUseCase(
//                input,
//                DEFAULT_SEARCH_SORT,
//                DEFAULT_SEARCH_PAGE,
//                DEFAULT_SEARCH_SIZE
//            )
//            if (response.isSuccessful) {

            val getBookmarkList = getFavoriteBookUseCase
//                response.body()?.let { body ->
//                    val searchList = body.kakaoBookItems
//                    searchList.map { searchItem ->
//                        if (getBookmarkList.contains(searchItem.toBookmarkItem())) {
//                            searchItem.isBookmark = true
//                        }
//                    }
//                    onChangedViewState(SearchViewState.GetSearchResult(body.kakaoBookItems))
//                }
//            }
        }
    }

    fun addBookMark(item: KakaoBook) {
        viewModelScope.launch(Dispatchers.IO) {
            val addBookmarkResult = insertBookmarkUseCase(item.toBookmarkItem().toKakaoBookmark())
//            onChangedViewState(
//                SearchViewState.AddBookmarkResult(
//                    addBookmarkResult >= 1L,
//                    item
//                ),
//            )
        }

    }


    fun deleteBookMark(item: KakaoBook) {
        viewModelScope.launch(Dispatchers.IO) {
            val deleteBookmarkResult =
                deleteBookmarkUseCase(item.toBookmarkItem().toKakaoBookmark())
//            onChangedViewState(
//                SearchViewState.DeleteBookmarkResult(
//                    deleteBookmarkResult == 1,
//                    item
//                )
//            )
        }
    }

    companion object {
        private const val DEFAULT_SEARCH_SORT = "accuracy"
        private const val DEFAULT_SEARCH_PAGE = 1
        private const val DEFAULT_SEARCH_SIZE = 30

    }
}