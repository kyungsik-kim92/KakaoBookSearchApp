package com.example.kakaobooksearchapp.ui.search

import com.example.domain.usecasse.GetFavoriteBookUseCase
import com.example.domain.usecasse.GetKakaoSearchBooksUseCase
import com.example.kakaobooksearchapp.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getKakaoSearchBooksUseCase: GetKakaoSearchBooksUseCase,
    private val getFavoriteBookUseCase: GetFavoriteBookUseCase
) : BaseViewModel() {


    val inputSearchStateFlow = MutableStateFlow("")


    fun searchBooks() {
        inputSearchStateFlow.value.let { input ->
            val response = getKakaoSearchBooksUseCase(
                input,
                DEFAULT_SEARCH_SORT,
                DEFAULT_SEARCH_PAGE,
                DEFAULT_SEARCH_SIZE
            )
            response.onEach { result ->
//                val getBookmarkList = getFavoriteBookUseCase
//                val searchList = result.list
//                searchList.map { searchItem ->
//                    if (getBookmarkList.contains(searchItem.toBookmarkItem())) {
//                        searchItem.isBookmark = true
//                    }
//                }
                onChangedViewState(SearchViewState.GetSearchResult(result.list))
            }

        }
    }

//    fun searchBooks() = viewModelScope.launch(Dispatchers.IO) {
//        inputSearchStateFlow.value.let { input ->
//
//            val response = getKakaoSearchBooksUseCase.invoke(
//                input,
//                DEFAULT_SEARCH_SORT,
//                DEFAULT_SEARCH_PAGE,
//                DEFAULT_SEARCH_SIZE
//            )
//            if (response.isSuccessful) {
//
//                val getBookmarkList = bookmarkRepository.getFavoriteBooks()
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
//        }
//    }

//    fun addBookMark(item: KakaoBook) {
//        viewModelScope.launch(Dispatchers.IO) {
//            val addBookmarkResult = bookmarkRepository.insertBook(item.toBookmarkItem())
//            onChangedViewState(
//                SearchViewState.AddBookmarkResult(
//                    addBookmarkResult >= 1L,
//                    item
//                ),
//            )
//        }
//
//    }
//
//
//    fun deleteBookMark(item: KakaoBook) {
//        viewModelScope.launch(Dispatchers.IO) {
//            val deleteBookmarkResult = bookmarkRepository.deleteBook(item.toBookmarkItem())
//            onChangedViewState(
//                SearchViewState.DeleteBookmarkResult(
//                    deleteBookmarkResult == 1,
//                    item
//                )
//            )
//        }
//    }

    companion object {
        private const val DEFAULT_SEARCH_SORT = "accuracy"
        private const val DEFAULT_SEARCH_PAGE = 1
        private const val DEFAULT_SEARCH_SIZE = 30

    }
}

