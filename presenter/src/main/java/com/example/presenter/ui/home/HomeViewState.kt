package com.example.presenter.ui.home

import com.example.domain.model.KakaoBook
import com.example.presenter.base.ViewState

sealed class HomeViewState : ViewState {
    data class RouteBookInfo(val item: KakaoBook) : HomeViewState()

}