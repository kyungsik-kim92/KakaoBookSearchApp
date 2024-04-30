package com.example.domain.model

import com.example.kakaobooksearchapp.network.response.KakaoBookItem
import com.example.kakaobooksearchapp.network.response.Meta
import com.google.gson.annotations.SerializedName

data class KakaoSearchBook(
    val list: List<KakaoBook>,
    val meta: KakaoSearchMeta
)


data class KakaoBook(
    val authors: List<String>,
    val contents: String,
    val datetime: String,
    val isbn: String,
    val price: Int,
    val publisher: String,
    val salePrice: Int,
    val status: String,
    val thumbnail: String,
    val title: String,
    val translators: List<String>,
    val url: String,
    var isBookmark: Boolean = false
)

data class KakaoSearchMeta(
    val isEnd: Boolean,
    val pageableCount: Int,
    val totalCount: Int
)