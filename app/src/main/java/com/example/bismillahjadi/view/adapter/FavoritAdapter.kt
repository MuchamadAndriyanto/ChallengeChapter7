package com.example.bismillahjadi.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bismillahjadi.databinding.ItemFavBinding
import com.example.bismillahjadi.room.MovieFavorit

@Suppress("RedundantSuppression")
class FavoritAdapter (private val moviefav: List <MovieFavorit>) : RecyclerView.Adapter<FavoritAdapter.ViewHolder>() {

    class ViewHolder(var binding :ItemFavBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemFavBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)

    }

    @Suppress("DeferredResultUnused")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvTitle.text = moviefav[position].title
        holder.binding.tvDate.text = moviefav[position].date

        Glide.with(holder.itemView)
            .load("https://image.tmdb.org/t/p/w780${moviefav[position].image}")
            .into(holder.binding.imgView)

    }

    override fun getItemCount(): Int {
        return moviefav.size
    }
}
