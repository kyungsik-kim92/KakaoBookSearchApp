package com.example.data.mapper

import com.example.data.api.response.BookmarkItem
import com.example.domain.model.KakaoBookmark

fun KakaoBookmark.toBookmarkItem() = BookmarkItem(
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

fun BookmarkItem.toKakaoBookmark() = KakaoBookmark(
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