package com.example.kakaobooksearchapp.home

import androidx.lifecycle.viewModelScope
import com.example.kakaobooksearchapp.base.BaseViewModel
import com.example.kakaobooksearchapp.network.response.KakaoBookItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : BaseViewModel() {


    fun routeBookInfo(item: KakaoBookItem) {
        viewModelScope.launch {
            onChangedViewState(HomeViewState.RouteBookInfo(item))
        }
    }


}


