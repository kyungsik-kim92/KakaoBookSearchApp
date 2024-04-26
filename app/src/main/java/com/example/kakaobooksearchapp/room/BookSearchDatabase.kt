package com.example.kakaobooksearchapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import androidx.room.TypeConverters
import com.example.kakaobooksearchapp.util.TypeConverter

@Database(
    entities = [BookMarkItem::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(TypeConverter::class)
abstract class BookSearchDatabase : RoomDatabase() {

    abstract fun bookSearchDao(): BookSearchDao



    companion object {
        fun getInstance(context: Context): BookSearchDatabase {
            return Room.databaseBuilder(context, BookSearchDatabase::class.java, "favorite-books").build()
        }
    }
}