package com.example.bismillahjadi.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bismillahjadi.model.ListMovie
import com.example.bismillahjadi.model.Result
import com.example.bismillahjadi.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListMovieTopViewModel: ViewModel(){
    private val movietoprat: MutableLiveData<List<com.example.bismillahjadi.model.Result>> by lazy {
        MutableLiveData<List<com.example.bismillahjadi.model.Result>>().also {
            getAllMovietoprated()
        }
    }

    fun getMovies(): LiveData<List<Result>> {
        return movietoprat
    }

    private fun getAllMovietoprated() {
        ApiClient.instance.allMoviesTopRated().enqueue(object : Callback<ListMovie> {
            override fun onResponse(call: Call<ListMovie>, response: Response<ListMovie>) {

                movietoprat.value = response.body()?.results

            }

            override fun onFailure(call: Call<ListMovie>, t: Throwable) {
                Log.d("Tag", t.message.toString())

            }

        })
    }
}