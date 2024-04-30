package com.example.kakaobooksearchapp.ui.bookmark

import androidx.lifecycle.viewModelScope
import com.example.domain.usecasse.GetFavoriteBookUseCase
import com.example.kakaobooksearchapp.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(private val getFavoriteBookUseCase: GetFavoriteBookUseCase) :
    BaseViewModel() {
//    private val _items = MutableStateFlow<List<BookMarkItem>>(emptyList())
//    val items: MutableStateFlow<List<BookMarkItem>> = _items

    init {
        getFavoriteBooks()
    }

    fun getFavoriteBooks() {
        viewModelScope.launch(Dispatchers.IO) {
            val bookmarkList = getFavoriteBookUseCase.invoke()
            withContext(Dispatchers.Main) {
                onChangedViewState(BookmarkViewState.BookmarkResult(bookmarkList))
            }
        }


    }

}