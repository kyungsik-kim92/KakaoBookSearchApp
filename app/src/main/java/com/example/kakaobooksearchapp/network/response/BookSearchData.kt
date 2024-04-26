package com.example.kakaobooksearchapp.network.response


import com.example.kakaobooksearchapp.network.response.KakaoBookItem
import com.example.kakaobooksearchapp.network.response.Meta
import com.google.gson.annotations.SerializedName

data class BookSearchData(
    @SerializedName("documents")
    val kakaoBookItems: List<KakaoBookItem>,
    @SerializedName("meta")
    val meta: Meta
)