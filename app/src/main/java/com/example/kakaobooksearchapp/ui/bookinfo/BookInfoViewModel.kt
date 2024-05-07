package com.example.kakaobooksearchapp.ui.bookinfo

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.domain.model.KakaoBook
import com.example.domain.usecasse.GetDeleteBookUseCase
import com.example.domain.usecasse.GetInsertBookUseCase
import com.example.kakaobooksearchapp.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BookInfoViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getInsertBookUseCase: GetInsertBookUseCase,
    private val getDeleteBookUseCase: GetDeleteBookUseCase,

) : BaseViewModel() {

    private val kakaoBookItem = savedStateHandle.get<KakaoBook>("item")

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


    private fun addBookMark(item: KakaoBook) {
//        viewModelScope.launch(Dispatchers.IO) {
//            val addBookmarkResult = getInsertBookUseCase(item.toBookmarkItem())
//            onChangedViewState(BookInfoViewState.AddBookmarkResult(addBookmarkResult >= 1L, item))
//            withContext(Dispatchers.Main) {
//                isBookmark.set(true)
//            }
//        }
    }

    private fun deleteBookMark(item: KakaoBook) {
        viewModelScope.launch(Dispatchers.IO) {
//            val deleteBookmarkResult = getDeleteBookUseCase(item.toBookmarkItem())
//            onChangedViewState(
//                BookInfoViewState.DeleteBookmarkResult(
//                    deleteBookmarkResult == 1,
//                    item
//                )
//            )
            withContext(Dispatchers.Main){
                isBookmark.set(false)
            }
        }
    }

}