package com.example.presenter.home

import androidx.lifecycle.viewModelScope
import com.example.domain.model.KakaoBook
import com.example.presenter.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : BaseViewModel() {


    fun routeBookInfo(item: KakaoBook) {
        viewModelScope.launch {
            onChangedViewState(HomeViewState.RouteBookInfo(item))
        }
    }


}

