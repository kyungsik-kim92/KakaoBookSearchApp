package com.example.presenter.ui.bookinfo

import androidx.lifecycle.SavedStateHandle
import com.example.domain.model.KakaoBook
import com.example.presenter.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class BookInfoViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle

) : BaseViewModel() {

    private val kakaoBookItem = savedStateHandle.get<KakaoBook>("item")
    val initBookmarkState = MutableStateFlow(false)

    init {
        kakaoBookItem?.isBookmark?.let {
            initBookmarkState.value = it
        }
    }


    fun onCheckedChanged(check: Boolean) {
        if (check) {
            onChangedViewEvent(BookInfoViewEvent.AddBookmark)
        } else {
            onChangedViewEvent(BookInfoViewEvent.DeleteBookmark)
        }
    }


}