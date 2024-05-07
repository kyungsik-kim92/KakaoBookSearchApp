package com.example.kakaobooksearchapp.ui.bookinfo

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.data.mapper.toBookmarkItem
import com.example.data.mapper.toKakaoBookmark
import com.example.domain.model.KakaoBook
import com.example.domain.usecase.DeleteBookmarkUseCase
import com.example.domain.usecase.InsertBookmarkUseCase
import com.example.kakaobooksearchapp.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BookInfoViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val insertBookmarkUseCase: InsertBookmarkUseCase,
    private val deleteBookmarkUseCase: DeleteBookmarkUseCase
) : BaseViewModel() {

    private val kakaoBookItem = savedStateHandle.get<KakaoBook>("item")

//    val isBookmark = ObservableBoolean()

    fun onCheckedChanged(check: Boolean) {

        kakaoBookItem?.let {
            if (check) {
                addBookMark(kakaoBookItem)
            } else {
                deleteBookMark(kakaoBookItem)
            }
        }
    }


    private fun addBookMark(item: KakaoBook) {
        viewModelScope.launch(Dispatchers.IO) {
            val addBookmarkResult = insertBookmarkUseCase(item.toBookmarkItem().toKakaoBookmark())
//            onChangedViewState(BookInfoViewState.AddBookmarkResult(addBookmarkResult >= 1L, item))
            withContext(Dispatchers.Main) {
//                isBookmark.set(true)
            }
        }
    }

    private fun deleteBookMark(item: KakaoBook) {
        viewModelScope.launch(Dispatchers.IO) {
            val deleteBookmarkResult =
                deleteBookmarkUseCase(item.toBookmarkItem().toKakaoBookmark())
//            onChangedViewState(
//                BookInfoViewState.DeleteBookmarkResult(
//                    deleteBookmarkResult == 1,
//                    item
//                )
//            )
            withContext(Dispatchers.Main) {
//                isBookmark.set(false)
            }
        }
    }

}