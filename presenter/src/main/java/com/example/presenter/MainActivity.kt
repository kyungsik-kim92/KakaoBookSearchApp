package com.example.presenter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.presenter.base.BaseViewModel
import com.example.presenter.base.ViewState
import com.example.domain.model.KakaoBookmark
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}

@HiltViewModel
class MainViewModel @Inject constructor() : BaseViewModel() {

    fun addBookmark(item: KakaoBookmark) {
        onChangedViewState(MainViewState.AddBookmark(item))
    }

    fun deleteBookmark(item: KakaoBookmark) {
        onChangedViewState(MainViewState.DeleteBookmark(item))
    }

}


sealed class MainViewState : ViewState {
    data class AddBookmark(val item: KakaoBookmark) : MainViewState()
    data class DeleteBookmark(val item: KakaoBookmark) : MainViewState()
}