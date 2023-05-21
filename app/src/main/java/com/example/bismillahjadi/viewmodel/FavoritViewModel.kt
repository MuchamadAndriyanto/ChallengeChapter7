package com.example.bismillahjadi.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.bismillahjadi.room.FavoritDao
import com.example.bismillahjadi.room.FavoritDatabase
import com.example.bismillahjadi.room.MovieFavorit
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@Suppress("unused")
@HiltViewModel
class FavoritViewModel @Inject constructor(app: Application) : AndroidViewModel(app){

    private var favDao : FavoritDao?=null
    private var favDb : FavoritDatabase?=null

    private var liveDataListfav: MutableLiveData<List<MovieFavorit>> = MutableLiveData()

    init {
        getAllMoviePopular()
    }


    fun getliveDataMoviefav(): MutableLiveData<List<MovieFavorit>> {
        return  liveDataListfav
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun getAllMoviePopular() {

        GlobalScope.launch {
            val dataDao = FavoritDatabase.getInstance(getApplication())!!.favoritDao()
            val listNote = dataDao.getAllMovieFavorit()
            liveDataListfav.postValue(listNote)

        }
    }



    suspend fun delete(favoritMovie : MovieFavorit) {
        val dataDao = FavoritDatabase.getInstance(getApplication())!!.favoritDao()
        dataDao.deleteFilmFavorit(favoritMovie)
        getAllMoviePopular()
    }

    suspend fun insert(favoritMovie : MovieFavorit){
        val dataDao = FavoritDatabase.getInstance(getApplication())!!.favoritDao()
        dataDao.addToFavorit(favoritMovie)
        getAllMoviePopular()
    }

    fun check(id: Int){
        val dataDao = FavoritDatabase.getInstance(getApplication())!!.favoritDao()
        dataDao.checkMovie(id)
        getAllMoviePopular()
    }



}




