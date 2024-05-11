package com.example.presenter.ui.bookmark

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetFavoriteBookmarkUseCase
import com.example.presenter.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(private val getFavoriteBookmarkUseCase: GetFavoriteBookmarkUseCase) :
    BaseViewModel(), LifecycleEventObserver {

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_RESUME -> {
                getFavoriteBookmarkUseCase().map {
                    onChangedViewEvent(BookmarkViewEvent.BookmarkResult(it))
                }.launchIn(viewModelScope)
            }

            else -> Unit
        }
    }
}




