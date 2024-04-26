package com.example.kakaopractice.network.response


import com.google.gson.annotations.SerializedName

data class BookSearchData(
    @SerializedName("documents")
    val kakaoBookItems: List<KakaoBookItem>,
    @SerializedName("meta")
    val meta: Meta
)