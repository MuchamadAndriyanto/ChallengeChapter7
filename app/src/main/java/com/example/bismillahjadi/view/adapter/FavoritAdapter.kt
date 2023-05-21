package com.example.bismillahjadi.view.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bismillahjadi.MainActivity
import com.example.bismillahjadi.R
import com.example.bismillahjadi.databinding.ItemFavBinding
import com.example.bismillahjadi.model.DetailMovieTop
import com.example.bismillahjadi.room.FavoritDatabase
import com.example.bismillahjadi.room.MovieFavorit
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

@Suppress("RedundantSuppression", "unused", "unused", "unused")
class FavoritAdapter (@Suppress("unused") private var context : Context, private var moviefav: List <MovieFavorit>) : RecyclerView.Adapter<FavoritAdapter.ViewHolder>() {

    private var filmFavDB: FavoritDatabase? = null
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

        holder.binding.detailToprated.setOnClickListener {
            val id = moviefav[position].id
            val image = moviefav[position].image
            val title = moviefav[position].title
            val date = moviefav[position].date
            val overview = moviefav[position].overview

            val detail = DetailMovieTop(id,image,title,date,overview)
            val bundle = Bundle()
            bundle.putParcelable("data_movietop", detail)
            Navigation.findNavController(it).navigate(R.id.action_favoritFragment_to_detailFragment,bundle)
        }

        holder.binding.cbFav.setOnClickListener {
            var isFavorites = holder.binding.cbFav.isChecked
            if (isFavorites){
                filmFavDB = FavoritDatabase.getInstance(it.context)

                AlertDialog.Builder(it.context)
                    .setTitle("Hapus Data")
                    .setMessage("Apakah Anda Ingin Menghapus Item")
                    .setPositiveButton("Iya") { _: DialogInterface, _: Int ->
                        GlobalScope.async {
                            val result = filmFavDB?.favoritDao()?.deleteFilmFavorit(
                                moviefav[position])

                            (holder.itemView.context as MainActivity).runOnUiThread {
                                if (result != 0) {
                                    Toast.makeText(it.context, "Item ${moviefav[position].title} Terhapus", Toast.LENGTH_LONG).show()
                                } else {
                                    Toast.makeText(it.context, "Item ${moviefav[position].title} Gagal terhapus", Toast.LENGTH_LONG).show()
                                }
                            }
                        }
                    }
                    .setNegativeButton("Tidak") { dialogInterface: DialogInterface, _: Int ->
                        dialogInterface.dismiss()
                        isFavorites = holder.binding.cbFav.isChecked
                    }
                    .show()


            }
        }




    }

    override fun getItemCount(): Int {
        return moviefav.size
    }
    fun setMovie(itemMovie: ArrayList<MovieFavorit>) {
        this.moviefav = itemMovie
    }
}
