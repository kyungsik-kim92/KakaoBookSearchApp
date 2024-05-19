package com.example.presenter

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.example.data.mapper.toKakaoBookmark
import com.example.domain.model.KakaoBook
import com.example.domain.model.KakaoBookmark
import com.example.domain.usecase.DeleteBookmarkUseCase
import com.example.domain.usecase.InsertBookmarkUseCase
import com.example.presenter.base.BaseActivity
import com.example.presenter.base.BaseViewModel
import com.example.presenter.base.ViewEvent
import com.example.presenter.base.ViewState
import com.example.presenter.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val mainViewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel.viewEvent.map(::onChangedViewEvent).launchIn(lifecycleScope)

    }

    private fun onChangedViewEvent(event: ViewEvent) {
        when (event) {
            is MainViewEvent.AddBookmark -> {
                Snackbar.make(binding.root, "북마크가 추가 되었습니다.", Snackbar.LENGTH_SHORT).show()
            }

            is MainViewEvent.DeleteBookmark -> {
                Snackbar.make(binding.root, "북마크가 해제 되었습니다.", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

}

@HiltViewModel
class MainViewModel @Inject constructor(
    private val insertBookmarkUseCase: InsertBookmarkUseCase,
    private val deleteBookmarkUseCase: DeleteBookmarkUseCase
) : BaseViewModel() {

    fun addBookmark(item: KakaoBook) = viewModelScope.launch(Dispatchers.IO) {
        insertBookmarkUseCase(item.toKakaoBookmark())
        onChangedViewEvent(MainViewEvent.AddBookmark(item.toKakaoBookmark()))
    }

    fun deleteBookmark(item: KakaoBook) = viewModelScope.launch(Dispatchers.IO) {
        deleteBookmarkUseCase(item.toKakaoBookmark())
        onChangedViewEvent(MainViewEvent.DeleteBookmark(item.toKakaoBookmark()))
    }
}


sealed class MainViewState : ViewState
sealed interface MainViewEvent : ViewEvent {
    data class AddBookmark(val item: KakaoBookmark) : MainViewEvent
    data class DeleteBookmark(val item: KakaoBookmark) : MainViewEvent
}