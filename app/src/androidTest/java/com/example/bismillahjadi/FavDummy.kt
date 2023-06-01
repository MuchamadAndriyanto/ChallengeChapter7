package com.example.bismillahjadi

import com.example.bismillahjadi.room.MovieFavorit

object FavDummy {

    fun generateDummyFavourite():List<MovieFavorit>{
        val favoriteList = ArrayList<MovieFavorit>()
        for (i in 0..5){
            val favorite =MovieFavorit(
                id = i,
                title = "title $i",
                date = "2022-03-0$i",
                image = "/$i.jpg",
                overview = "overview $i"
            )
            favoriteList.add(favorite)
        }
        return favoriteList
    }

}