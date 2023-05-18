package com.example.bismillahjadi.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailMovieTop(
    val id : Int,
    val imagepath: String,
    val title: String,
    val date: String,
    val overview: String
) : Parcelable
