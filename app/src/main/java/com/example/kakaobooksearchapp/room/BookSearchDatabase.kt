package com.example.kakaobooksearchapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters

@Database(
    entities = [BookMarkItem::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(TypeConverter::class)
abstract class BookSearchDatabase : RoomDatabase(){

    abstract fun bookSearchDao(): BookSearchDao


}