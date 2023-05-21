package com.example.bismillahjadi.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [MovieFavorit::class],version = 1
)

abstract class FavoritDatabase : RoomDatabase() {

    abstract fun favoritDao() : FavoritDao

    companion object{
        private var INSTANCE : FavoritDatabase? = null

        fun getInstance(context : Context): FavoritDatabase? {
            if (INSTANCE == null){
                synchronized( FavoritDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        FavoritDatabase::class.java,"favorit.db").build()
                }
            }
            return INSTANCE
        }

    }

}