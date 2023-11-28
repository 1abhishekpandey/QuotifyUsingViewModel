package com.example.quotify.DatabaseHandler

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Quote::class], version = 1)
abstract class QuoteDatabase: RoomDatabase() {
    abstract fun quoteDAO(): QuoteDAO

    companion object {
        @Volatile
        private var INSTANCE: QuoteDatabase? = null

        fun getInstance(context: Context): QuoteDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        QuoteDatabase::class.java,
                        "quote_database"
                    )
                        .createFromAsset("quotes.db")
                        .build()
                }
            }
            return INSTANCE as QuoteDatabase
        }
    }

}