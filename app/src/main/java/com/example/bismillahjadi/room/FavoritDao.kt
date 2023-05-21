package com.example.bismillahjadi.room

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
    fun getAllMovieFavorit() : List<MovieFavorit>
    @Insert
    suspend fun addToFavorit(favoritMovie : MovieFavorit) : Long
    @Query("SELECT count(*) FROM MovieFavorit WHERE MovieFavorit.id = :id")
    fun checkMovie(id: Int) : Int
    @Delete
    suspend fun deleteFilmFavorit(filmFavorites: MovieFavorit) : Int

}