package com.example.domain.model

data class KakaoBookmark(
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
)

fun KakaoBookmark.toKakaoBook(): KakaoBook = KakaoBook(
    authors,
    contents,
    datetime,
    isbn,
    price,
    publisher,
    salePrice,
    status,
    thumbnail,
    title,
    translators,
    url
)