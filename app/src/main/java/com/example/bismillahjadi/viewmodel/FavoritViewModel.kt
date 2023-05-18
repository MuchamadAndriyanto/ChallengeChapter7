package com.example.bismillahjadi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bismillahjadi.room.FavoritDao
import com.example.bismillahjadi.room.MovieFavorit
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritViewModel @Inject constructor(private val favoriteDAO: FavoritDao): ViewModel( ){

    //insert favorite movie
    suspend fun insertFavoriteMovie( favorite: MovieFavorit) = favoriteDAO.insertFilmFavorites(favorite)

    fun insertMovie(id:Int,title:String,date:String,image:String){
        viewModelScope.launch {
            val movie = MovieFavorit(id,title,date,image)
            favoriteDAO.insertFilmFavorites(movie)
        }
    }
    fun getFavoriteMovie() = favoriteDAO.getAllFilmFavorites()
}

