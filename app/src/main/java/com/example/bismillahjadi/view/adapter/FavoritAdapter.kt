package com.example.bismillahjadi.view.adapter

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bismillahjadi.MainActivity
import com.example.bismillahjadi.databinding.ItemFavBinding
import com.example.bismillahjadi.room.FavoritDatabase
import com.example.bismillahjadi.room.MovieFavorit
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class FavoritAdapter (private val moviefav: List <MovieFavorit>) : RecyclerView.Adapter<FavoritAdapter.ViewHolder>() {
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

        holder.binding.cbFav.setOnClickListener {
            var isFavorites = holder.binding.cbFav.isChecked
            if (isFavorites){
                filmFavDB = FavoritDatabase.getInstance(it.context)

                AlertDialog.Builder(it.context)
                    .setTitle("Hapus Data")
                    .setMessage("Yakin Hapus Data")
                    .setPositiveButton("Ya") { _: DialogInterface, _: Int ->
                        GlobalScope.async {
                            val result = filmFavDB?.favDao()?.deleteFilmFavorites(
                                moviefav[position])

                            (holder.itemView.context as MainActivity).runOnUiThread {
                                if (result != 0) {
                                    Toast.makeText(it.context, "Data ${moviefav[position].title} Terhapus", Toast.LENGTH_LONG).show()
                                } else {
                                    Toast.makeText(it.context, "Data ${moviefav[position].title} Gagal terhapus", Toast.LENGTH_LONG).show()
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
}
