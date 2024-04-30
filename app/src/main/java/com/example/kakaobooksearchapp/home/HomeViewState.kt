package com.example.kakaobooksearchapp.home

import com.example.domain.model.KakaoBook
import com.example.kakaobooksearchapp.base.ViewState

sealed class HomeViewState : ViewState {
    data class RouteBookInfo(val item: KakaoBook) : HomeViewState()

}