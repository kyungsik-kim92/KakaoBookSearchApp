package com.example.kakaobooksearchapp.ui.bookinfo

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kakaobooksearchapp.BookmarkResult
import com.example.kakaobooksearchapp.network.response.KakaoBookItem
import com.example.kakaobooksearchapp.ui.bookmark.BookmarkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BookInfoViewModel(
    private val bookmarkRepository: BookmarkRepository, savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _bookMarkItems = MutableLiveData<BookmarkResult>()
    val bookMarkItems: LiveData<BookmarkResult> = _bookMarkItems

    private val addBookState = MutableLiveData<KakaoBookItem>()
    private val deleteBookState = MutableLiveData<KakaoBookItem>()


    private val kakaoBookItem = savedStateHandle.get<KakaoBookItem>("item")

    val isBookmark = ObservableBoolean(kakaoBookItem?.isBookmark ?: false)

    fun onCheckedChanged(check: Boolean) {

        kakaoBookItem?.let {
            if (check) {
                addBookmark(kakaoBookItem)
            } else {
                deleteBookmark(kakaoBookItem)
            }
        }
    }


    private fun addBookmark(item: KakaoBookItem) {
        viewModelScope.launch(Dispatchers.IO) {
            val addBookmarkResult = bookmarkRepository.insertBook(item.toBookmarkItem())
            _bookMarkItems.postValue(
                BookmarkResult.AddBookmarkResult(
                    addBookmarkResult >= 1L,
                    item
                )
            )
            withContext(Dispatchers.Main) {
                isBookmark.set(true)
            }
        }
    }

    private fun deleteBookmark(item: KakaoBookItem) {
        viewModelScope.launch(Dispatchers.IO) {
            val deleteBookmarkResult = bookmarkRepository.deleteBook(item.toBookmarkItem())
            _bookMarkItems.postValue(
                BookmarkResult.DeleteBookmarkResult(
                    deleteBookmarkResult == 1,
                    item
                )
            )
            withContext(Dispatchers.Main) {
                isBookmark.set(false)
            }
        }
    }

    sealed class BookmarkViewState  {
        data class AddBookmark(val item: KakaoBookItem) : BookmarkViewState()
        data class DeleteBookmark(val item: KakaoBookItem) : BookmarkViewState()
    }


}