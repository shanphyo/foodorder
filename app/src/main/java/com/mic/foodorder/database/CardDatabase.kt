package com.mic.roomfragment.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mic.foodorder.database.Card
import com.mic.foodorder.database.CartDao


@Database(entities = arrayOf(Card::class),version = 1)
abstract class CardDatabase : RoomDatabase(){
    abstract fun cardDao(): CartDao
    companion object {
        private var INSTANCE: CardDatabase? = null
        fun getDatabase(context: Context): CardDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance =
                    Room.databaseBuilder(context, CardDatabase::class.java, "card_database")
                        .allowMainThreadQueries().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}