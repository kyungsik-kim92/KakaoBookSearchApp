package com.example.kakaobooksearchapp.ui.bookinfo

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.kakaobooksearchapp.base.BaseViewModel
import com.example.kakaobooksearchapp.network.response.KakaoBookItem
import com.example.kakaobooksearchapp.data.repo.BookmarkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BookInfoViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val bookmarkRepository: BookmarkRepository
) : BaseViewModel() {

    private val kakaoBookItem = savedStateHandle.get<KakaoBookItem>("item")

    val isBookmark = ObservableBoolean(kakaoBookItem?.isBookmark ?: false)

    fun onCheckedChanged(check: Boolean) {

        kakaoBookItem?.let {
            if (check) {
                addBookMark(kakaoBookItem)
            } else {
                deleteBookMark(kakaoBookItem)
            }
        }
    }


    private fun addBookMark(item: KakaoBookItem) {
        viewModelScope.launch(Dispatchers.IO) {
            val addBookmarkResult = bookmarkRepository.insertBook(item.toBookmarkItem())
            onChangedViewState(BookInfoViewState.AddBookmarkResult(addBookmarkResult >= 1L, item))
            withContext(Dispatchers.Main) {
                isBookmark.set(true)
            }
        }
    }

    private fun deleteBookMark(item: KakaoBookItem) {
        viewModelScope.launch(Dispatchers.IO) {
            val deleteBookmarkResult = bookmarkRepository.deleteBook(item.toBookmarkItem())
            onChangedViewState(
                BookInfoViewState.DeleteBookmarkResult(
                    deleteBookmarkResult == 1,
                    item
                )
            )
            withContext(Dispatchers.Main){
                isBookmark.set(false)
            }
        }
    }

}