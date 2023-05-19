@file:Suppress("RedundantNullableReturnType", "RedundantNullableReturnType",
    "RedundantNullableReturnType", "RedundantNullableReturnType", "RedundantSuppression",
    "RedundantSuppression", "RedundantSuppression", "RedundantSuppression", "unused"
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
import com.example.bismillahjadi.viewmodel.FavoritViewModel
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION", "DeferredResultUnused", "DeferredResultUnused", "unused", "unused")
@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private lateinit var favViewModel: FavoritViewModel

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

        favViewModel = ViewModelProvider(this)[FavoritViewModel::class.java]



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


        binding.btnFav.setOnClickListener {
            favViewModel.insertMovie(id, title, date, imagepath)
            Toast.makeText(context, "Berhasil Menambahkan Item Ke Favorit", Toast.LENGTH_SHORT).show()
        }
    }
}