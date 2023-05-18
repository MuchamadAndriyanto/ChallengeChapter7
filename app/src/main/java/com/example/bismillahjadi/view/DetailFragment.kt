@file:Suppress("RedundantNullableReturnType", "RedundantNullableReturnType",
    "RedundantNullableReturnType", "RedundantNullableReturnType", "RedundantSuppression",
    "RedundantSuppression", "RedundantSuppression", "RedundantSuppression", "unused"
)

package com.example.bismillahjadi.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.bismillahjadi.databinding.FragmentDetailBinding
import com.example.bismillahjadi.model.DetailMovieTop
import com.example.bismillahjadi.room.FavoritDao
import com.example.bismillahjadi.room.FavoritDatabase
import com.example.bismillahjadi.room.MovieFavorit
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

@Suppress("DEPRECATION", "DeferredResultUnused", "DeferredResultUnused", "unused", "unused")
@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private var favDao : FavoritDao?=null
    private var favDb : FavoritDatabase?=null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favDb= FavoritDatabase.getInstance(requireContext())
        favDao = favDb?.favDao()


        // pengambilan data
        val list = arguments?.getParcelable<DetailMovieTop>("data_movietop") as DetailMovieTop
        val title = list.title
        val date = list.date
        val overview = list.overview
        val imagepath = list.imagepath

        binding.tvNamaMovie.text = title
        binding.tvDate.text = date
        binding.tvOverview.text = overview
        Glide.with(view.context).load("https://image.tmdb.org/t/p/w780${imagepath}")
            .into(binding.ivDetailGambar)


        binding.btnFav.setOnClickListener {

            val getFav = arguments?.getParcelable<DetailMovieTop>("data_movietop") as DetailMovieTop
            val title = getFav.title
            val date = getFav.date
            val gambar = getFav.imagepath

            GlobalScope.async {
                val favfilm =  MovieFavorit(0,title,date,gambar)
                val result =favDb?.favDao()?.insertFilmFavorites(favfilm)
                activity?.runOnUiThread {
                    Toast.makeText(context, "Berhasil Menambahkan Item Ke Favorite", Toast.LENGTH_LONG)
                        .show()
                    Log.d("tes2", result.toString())
                    Log.d("tes3", title)
                }

            }

        }
    }
}