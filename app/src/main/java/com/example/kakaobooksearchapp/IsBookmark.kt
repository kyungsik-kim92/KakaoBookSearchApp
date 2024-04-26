package com.example.kakaobooksearchapp

import com.example.kakaobooksearchapp.network.response.KakaoBookItem

data class IsBookmark(
    val result: Boolean, val item: KakaoBookItem
)
