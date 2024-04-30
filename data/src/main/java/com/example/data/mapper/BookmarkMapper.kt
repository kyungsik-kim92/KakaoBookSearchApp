package com.example.data.mapper

import com.example.data.api.response.BookMarkItem
import com.example.domain.model.KakaoBookmark


fun KakaoBookmark.toBookmarkItem() = BookMarkItem(
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

fun BookMarkItem.toKakaoBookmark() = KakaoBookmark(
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
