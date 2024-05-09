package com.example.kakaobooksearchapp.ext

import com.example.domain.model.KakaoBook
import com.example.domain.model.KakaoBookmark

fun KakaoBook.toKakaoBookmark() = KakaoBookmark(
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