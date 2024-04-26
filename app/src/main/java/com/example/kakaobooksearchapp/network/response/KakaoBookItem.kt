package com.example.kakaobooksearchapp.network.response


import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "book")
data class KakaoBookItem(
    @SerializedName("authors")
    val authors: List<String>,
    @SerializedName("contents")
    val contents: String,
    @SerializedName("datetime")
    val datetime: String,
    @SerializedName("isbn")
    val isbn: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("publisher")
    val publisher: String,
    @SerializedName("sale_price")
    val salePrice: Int,
    @SerializedName("status")
    val status: String,
    @SerializedName("thumbnail")
    val thumbnail: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("translators")
    val translators: List<String>,
    @SerializedName("url")
    val url: String,
    var isBookmark: Boolean = false
) : Parcelable {
//    fun dateTime(): String {
//        return datetime.substring(0 until 4)
//    }
//
//    fun stringAuthors(): String {
//        return authors.joinToString(", ")
//    }
//
//
//    fun toBookmarkItem() = BookMarkItem(
//        authors = authors,
//        contents = contents,
//        datetime = datetime,
//        isbn = isbn,
//        price = price,
//        publisher = publisher,
//        salePrice = salePrice,
//        status = status,
//        thumbnail = thumbnail,
//        title = title,
//        translators = translators,
//        url = url,
//    )

}
