package com.example.data.mapper

import com.example.data.api.response.BookSearchData
import com.example.data.api.response.BookmarkItem
import com.example.data.api.response.KakaoBookItem
import com.example.data.api.response.Meta
import com.example.domain.model.KakaoBook
import com.example.domain.model.KakaoSearchBook
import com.example.domain.model.KakaoSearchMeta

fun KakaoBookItem.toKakaoBook() = KakaoBook(
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

fun Meta.toKakaoSearchMeta() = KakaoSearchMeta(
    isEnd, pageableCount, totalCount
)


fun BookSearchData.toKakaoSearchBook() = KakaoSearchBook(
    kakaoBookItems.map { it.toKakaoBook() },
    meta.toKakaoSearchMeta()
)

fun KakaoBook.toBookmarkItem() = BookmarkItem(
    authors, contents, datetime, isbn, price, publisher, salePrice, status, thumbnail, title, translators, url
)