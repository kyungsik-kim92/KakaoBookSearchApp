package com.example.kakaobooksearchapp

import com.example.kakaobooksearchapp.base.ViewState
import com.example.kakaobooksearchapp.network.response.KakaoBookItem

sealed class HomeViewState : ViewState {
    data class RouteBookInfo(val item: KakaoBookItem) : HomeViewState()

}