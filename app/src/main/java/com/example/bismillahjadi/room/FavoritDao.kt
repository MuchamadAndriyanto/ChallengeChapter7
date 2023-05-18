package com.example.bismillahjadi.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoritDao {

    @Query("SELECT * FROM MovieFavorit")
    fun getAllFilmFavorites() : LiveData<List<MovieFavorit>>

    @Insert
    suspend fun insertFilmFavorites(filmFavorites: MovieFavorit)

    @Delete
    suspend fun deleteFilmFavorites(filmFavorites: MovieFavorit) : Int

}