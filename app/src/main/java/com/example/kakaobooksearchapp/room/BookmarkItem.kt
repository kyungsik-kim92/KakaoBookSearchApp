package com.example.kakaobooksearchapp.room

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "book")
data class BookMarkItem(
    val authors: List<String>,
    val contents: String,
    val datetime: String,
    @PrimaryKey
    val isbn: String,
    val price: Int,
    val publisher: String,
    val salePrice: Int,
    val status: String,
    val thumbnail: String,
    val title: String,
    val translators: List<String>,
    val url: String
) : Parcelable {


    fun dateTime(): String {
        return datetime.substring(0 until 4)
    }

}