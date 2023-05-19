package com.example.bismillahjadi.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Suppress("unused", "unused", "unused", "unused", "RedundantSuppression", "RedundantSuppression",
    "RedundantSuppression", "RedundantSuppression"
)
@Dao
interface FavoritDao {

    @Query("SELECT * FROM MovieFavorit")
    fun getAllMovieFavorit() : LiveData<List<MovieFavorit>>

    @Insert
    suspend fun insertFilmFavorit(filmFavorites: MovieFavorit)

    @Delete
    suspend fun deleteFilmFavorit(filmFavorites: MovieFavorit) : Int

}