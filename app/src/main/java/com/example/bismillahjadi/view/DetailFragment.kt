@file:Suppress("RedundantNullableReturnType", "RedundantNullableReturnType",
    "RedundantNullableReturnType", "RedundantNullableReturnType", "RedundantSuppression",
    "RedundantSuppression", "RedundantSuppression", "RedundantSuppression", "unused",
    "LocalVariableName"
)

package com.example.bismillahjadi.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.bismillahjadi.databinding.FragmentDetailBinding
import com.example.bismillahjadi.model.DetailMovieTop
import com.example.bismillahjadi.room.FavoritDao
import com.example.bismillahjadi.room.FavoritDatabase
import com.example.bismillahjadi.room.MovieFavorit
import com.example.bismillahjadi.viewmodel.FavoritViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

@Suppress("DEPRECATION", "DeferredResultUnused", "DeferredResultUnused", "unused", "unused",
    "KotlinConstantConditions"
)
@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private var favDao: FavoritDao? = null
    private var favDb: FavoritDatabase? = null
    private lateinit var favViewModel: FavoritViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favViewModel = ViewModelProvider(this)[FavoritViewModel::class.java]

        favDb = FavoritDatabase.getInstance(requireContext())
        favDao = favDb?.favoritDao()

        // pengambilan data
       
        val list = arguments?.getParcelable<DetailMovieTop>("data_movietop") as DetailMovieTop
        val title = list.title
        val date = list.date
        val overview = list.overview
        val imagepath = list.imagepath
        val id = list.id

        binding.tvNamaMovie.text = title
        binding.tvDate.text = date
        binding.tvOverview.text = overview
        Glide.with(view.context).load("https://image.tmdb.org/t/p/w780${imagepath}")
            .into(binding.ivDetailGambar)


        binding.tbFav.setOnClickListener {
            GlobalScope.async {
                val getFavMov = arguments?.getParcelable<DetailMovieTop>("data_movietop") as DetailMovieTop
                val id = getFavMov.id
                val title = getFavMov.title
                val date = getFavMov.date
                val gambar = getFavMov.imagepath
                val overview = getFavMov.overview

                val detail = favDb?.favoritDao()?.addToFavorit(MovieFavorit(id, title, date, gambar,overview))

                activity?.runOnUiThread {
                    if (detail != 0.toLong()){
                        Toast.makeText(context, "Berhasil Menambahkan Data ke Favorit", Toast.LENGTH_LONG).show()
                        var _isChecked = false
                        _isChecked = !_isChecked
                        binding.tbFav.isChecked = _isChecked

                    }else{
                        Toast.makeText(context, "Gagal", Toast.LENGTH_LONG).show()
                    }
                }


            }


        }


    }

}

