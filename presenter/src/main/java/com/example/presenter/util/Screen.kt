package com.example.presenter.util

sealed class Screen(val route: String, val sendRoute: String) {
    data object Home : Screen("route_home", "")
    data class Detail(
        val passKakaoBookItem: String = "passKakaoBookItem"
    ) : Screen("route_detail/{$passKakaoBookItem}", "route_detail/$passKakaoBookItem")
}