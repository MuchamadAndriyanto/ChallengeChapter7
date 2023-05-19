package com.example.bismillahjadi.room

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [MovieFavorit::class],version = 1
)

abstract class FavoritDatabase : RoomDatabase() {

    abstract fun favoritDao() : FavoritDao

}