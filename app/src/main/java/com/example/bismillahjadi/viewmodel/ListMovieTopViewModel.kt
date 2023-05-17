package com.example.bismillahjadi.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bismillahjadi.model.ListMovie
import com.example.bismillahjadi.model.Result
import com.example.bismillahjadi.network.ApiClient
import com.example.bismillahjadi.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ListMovieTopViewModel @Inject constructor(private var api : ApiService): ViewModel(){

    var livedatamovie : MutableLiveData<List<Result>> = MutableLiveData()

    fun getlivedatamovie(): MutableLiveData<List<Result>> {
        return livedatamovie
    }

    fun getAllMovieTop() {
        api.allMoviesTopRated().enqueue(object : Callback<ListMovie> {
            override fun onResponse(call: Call<ListMovie>, response: Response<ListMovie>) {

                livedatamovie.value = response.body()?.results

            }

            override fun onFailure(call: Call<ListMovie>, t: Throwable) {
                Log.d("Tag", t.message.toString())

            }

        })
    }
}