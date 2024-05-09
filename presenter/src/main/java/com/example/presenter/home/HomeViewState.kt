package com.example.presenter.home

import com.example.domain.model.KakaoBook
import com.example.presenter.base.ViewState

sealed class HomeViewState : ViewState {
    data class RouteBookInfo(val item: KakaoBook) : HomeViewState()

}