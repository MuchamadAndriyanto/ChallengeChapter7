package com.example.bismillahjadi.room

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class MovieFavorit(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id : Int,
    @ColumnInfo(name = "title")
    var title : String,
    @ColumnInfo(name = "date")
    var date: String,
    @ColumnInfo(name = "image")
    var image : String

): Parcelable
