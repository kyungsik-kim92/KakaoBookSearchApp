package com.example.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

import androidx.room.TypeConverters
import com.example.data.api.response.BookmarkItem
import com.example.data.util.TypeConverter


@Database(
    entities = [BookmarkItem::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(TypeConverter::class)
abstract class BookSearchDatabase : RoomDatabase() {

    abstract fun bookSearchDao(): BookSearchDao

}