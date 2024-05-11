package com.example.presenter.ui.home

import com.example.domain.model.KakaoBook
import com.example.presenter.base.ViewEvent
import com.example.presenter.base.ViewState

sealed class HomeViewState : ViewState


sealed interface HomeViewEvent : ViewEvent {
    data class RouteBookInfo(val item: KakaoBook) : HomeViewEvent
}