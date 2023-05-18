package com.example.bismillahjadi.view.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bismillahjadi.R
import com.example.bismillahjadi.databinding.ItemListmovietopBinding
import com.example.bismillahjadi.model.DetailMovieTop
import com.example.bismillahjadi.model.Result

@Suppress("RemoveRedundantQualifierName")
class MovieTopAdapter(private var listMovieTop : List<Result>) : RecyclerView.Adapter<MovieTopAdapter.ViewHolder>() {
    class ViewHolder(var binding: ItemListmovietopBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemListmovietopBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieTopAdapter.ViewHolder, position: Int) {
        holder.binding.tvTitle.text = listMovieTop[position].title
        holder.binding.tvDate.text = listMovieTop[position].releaseDate
        Glide.with(holder.itemView).load("https://image.tmdb.org/t/p/w780${listMovieTop[position].backdropPath}").into(holder.binding.imgView)

        holder.binding.detailToprated.setOnClickListener {
            val imagepath = listMovieTop[position].backdropPath
            val title = listMovieTop[position].title
            val date = listMovieTop[position].releaseDate
            val overview = listMovieTop[position].overview
            val detail = DetailMovieTop(imagepath,title,date, overview)

            val data = Bundle()
            data.putParcelable("data_movietop",detail)
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_detailFragment,data)

        }

    }

    override fun getItemCount(): Int {
        return listMovieTop.size
    }

}