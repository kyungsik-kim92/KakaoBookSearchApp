package com.example.domain.model

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
){
    fun toBookmarkItem() = KakaoBookmark(
        authors = authors,
        contents = contents,
        datetime = datetime,
        isbn = isbn,
        price = price,
        publisher = publisher,
        salePrice = salePrice,
        status = status,
        thumbnail = thumbnail,
        title = title,
        translators = translators,
        url = url,
    )
}

data class KakaoSearchMeta(
    val isEnd: Boolean,
    val pageableCount: Int,
    val totalCount: Int
)


