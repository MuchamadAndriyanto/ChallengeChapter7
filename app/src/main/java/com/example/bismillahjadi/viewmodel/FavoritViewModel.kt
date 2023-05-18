package com.example.bismillahjadi.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bismillahjadi.network.ApiService
import com.example.bismillahjadi.room.FavoritDao
import com.example.bismillahjadi.room.FavoritDatabase
import com.example.bismillahjadi.room.MovieFavorit
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoritViewModel@Inject constructor(var api : ApiService) : ViewModel() {
    private var movefavDao: FavoritDao? = null
    private var movefavDb: FavoritDatabase? = null

    @SuppressLint("StaticFieldLeak")
    lateinit var context: Context


    private lateinit var liveDataListfav: MutableLiveData<List<MovieFavorit>>

    init {
        movefavDb = FavoritDatabase.getInstance(context)
        movefavDao = movefavDb!!.favDao()
    }


    fun getliveDataMoviefav(): MutableLiveData<List<MovieFavorit>> {
        return liveDataListfav
    }

}

