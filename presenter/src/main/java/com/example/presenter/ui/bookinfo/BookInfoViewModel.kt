package com.example.presenter.ui.bookinfo

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.mapper.toKakaoBookmark
import com.example.domain.model.KakaoBook
import com.example.domain.usecase.DeleteBookmarkUseCase
import com.example.domain.usecase.InsertBookmarkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookInfoViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val insertBookmarkUseCase: InsertBookmarkUseCase,
    private val deleteBookmarkUseCase: DeleteBookmarkUseCase
) : ViewModel() {

    private val kakaoBookItem = savedStateHandle.get<KakaoBook>("passKakaoBookItem")

    private val _bookInfoState = MutableStateFlow(BookInfoState())
    val bookInfoState = _bookInfoState.asStateFlow()


    val initBookmarkState = MutableStateFlow(false)

    init {
        kakaoBookItem?.isBookmark?.let {
            initBookmarkState.value = it
        }
        kakaoBookItem?.let {
            _bookInfoState.update {
                it.copy(item = kakaoBookItem)
            }
        }
    }

    fun toggleBookmark() {
        _bookInfoState.value.item?.let { item ->
            if (item.isBookmark) {
                viewModelScope.launch(Dispatchers.IO) {
                    deleteBookmarkUseCase(item.toKakaoBookmark())
                    _bookInfoState.update {
                        it.copy(item.copy(isBookmark = false))
                    }
                }
            } else {
                viewModelScope.launch(Dispatchers.IO) {
                    insertBookmarkUseCase(item.toKakaoBookmark())
                    _bookInfoState.update {
                        it.copy(item.copy(isBookmark = true))
                    }
                }
            }
        }
    }


}

data class BookInfoState(
    val item: KakaoBook? = null
)