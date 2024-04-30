package com.example.data.api.response


import com.google.gson.annotations.SerializedName

data class BookSearchData(
    @SerializedName("documents")
    val kakaoBookItems: List<KakaoBookItem>,
    @SerializedName("meta")
    val meta: Meta
)