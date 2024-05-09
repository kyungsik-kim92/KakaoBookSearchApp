package com.example.kakaobooksearchapp.ui.bookmark

import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetFavoriteBookmarkUseCase
import com.example.kakaobooksearchapp.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(private val getFavoriteBookmarkUseCase: GetFavoriteBookmarkUseCase) :
    BaseViewModel() {
    val bookmarkStateFlow = MutableStateFlow(true)

    init {
        getFavoriteBooks()
    }

    fun getFavoriteBooks() {
        getFavoriteBookmarkUseCase().map {
            bookmarkStateFlow.value = it.isNotEmpty()
            onChangedViewEvent(BookmarkViewEvent.BookmarkResult(it))
        }.launchIn(viewModelScope)

    }
}



